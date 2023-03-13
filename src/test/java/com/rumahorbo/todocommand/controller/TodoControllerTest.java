package com.rumahorbo.todocommand.controller;

import com.rumahorbo.todocommand.model.Todo;
import com.rumahorbo.todocommand.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class TodoControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private TodoRepository repository;

    @Test
    void updateTodo_shouldReturnUpdatedTodo_whenTodoIdIsExist() {
        Todo learning = new Todo("learning", false);
        Flux<Todo> actual = this.repository.deleteAll().thenMany(this.repository.save(learning)).thenMany(this.repository.findAll());
        StepVerifier.create(actual)
                .consumeNextWith(todo -> learning.setId(todo.getId()))
                .expectNextCount(0)
                .verifyComplete();

        this.client
                .put()
                .uri("/todo/" + learning.getId())
                .bodyValue(learning)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Todo.class)
                .isEqualTo(learning);
    }

    @Test
    void updateTodo_shouldReturnNull_whenTodoIdIsNotExist() {
        Todo learning = new Todo("learning", false);
        Flux<Todo> actual = this.repository.deleteAll().thenMany(this.repository.save(learning)).thenMany(this.repository.findAll());
        StepVerifier.create(actual)
                .consumeNextWith(todo -> learning.setId(todo.getId()))
                .expectNextCount(0)
                .verifyComplete();

        this.client
                .put()
                .uri("/todo/2")
                .bodyValue(learning)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

}
