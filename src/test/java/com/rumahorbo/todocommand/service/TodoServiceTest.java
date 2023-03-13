package com.rumahorbo.todocommand.service;

import com.rumahorbo.todocommand.model.Todo;
import com.rumahorbo.todocommand.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TodoServiceTest {

    @Autowired
    private TodoService service;

    @Autowired
    private TodoRepository repository;

    @Test
    void updateById_shouldReturnUpdatedTodo_whenTodoIdRequestIsExist() {
        Todo learning = new Todo("learning", false);
        Todo swimming = new Todo("swimming", false);
        Flux<Todo> actual = this.repository.deleteAll()
                .thenMany(this.repository.save(learning))
                .thenMany(this.repository.save(swimming))
                .thenMany(this.repository.findAll());
        StepVerifier.create(actual)
                .assertNext(to -> {
                    System.out.println(to);
                    assertEquals(learning, to);
                })
                .consumeNextWith(to -> {
                    System.out.println(to);
                    assertEquals(swimming.getTitle(), to.getTitle());
                })
                .expectNextCount(0)
                .verifyComplete();

        Mono<Todo> start = service.updateById(learning.getId(), learning);

        StepVerifier.create(start)
                .consumeNextWith(todo -> {
                    assertEquals(todo, learning);
                })
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void updateById_shouldReturnEmpty_whenTodoIdRequestIsNotExist() {
        Todo learning = new Todo("learning", false);
        Flux<Todo> actual = this.repository.deleteAll()
                .thenMany(this.repository.save(learning))
                .thenMany(this.repository.findAll());
        StepVerifier.create(actual)
                .assertNext(to -> {
                    assertEquals(learning, to);
                })
                .expectNextCount(0)
                .verifyComplete();

        Mono<Todo> start = service.updateById(learning.getId() + 1, learning);

        StepVerifier.create(start)
                .expectNextCount(0)
                .verifyComplete();
    }

}
