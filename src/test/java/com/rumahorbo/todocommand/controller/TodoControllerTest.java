package com.rumahorbo.todocommand.controller;

import com.rumahorbo.todocommand.model.Todo;
import com.rumahorbo.todocommand.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TodoController.class)
@Import(TodoService.class)
class TodoControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private TodoService service;

    @Test
    void putTodo() {
        int id = 1;
        Todo response = new Todo("learning", true);
        when(this.service.updateById(id, response)).thenReturn(Mono.just(response));

        this.client
                .put()
                .uri("/todo/" + id)
                .bodyValue(response)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Todo.class)
                .isEqualTo(response);
    }

}
