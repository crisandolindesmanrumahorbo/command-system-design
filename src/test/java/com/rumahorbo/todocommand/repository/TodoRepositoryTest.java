package com.rumahorbo.todocommand.repository;

import com.rumahorbo.todocommand.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository repository;

    @Test
    void findAll() {
        Todo learning = new Todo("learning", false);
        Todo swimming = new Todo("swimming", false);
        Flux<Todo> actual = this.repository.deleteAll()
                .thenMany(this.repository.save(learning))
                .thenMany(this.repository.save(swimming))
                .thenMany(this.repository.findAll());

        StepVerifier.create(actual)
                .expectNextCount(2)
                .verifyComplete();
    }
}
