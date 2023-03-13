package com.rumahorbo.todocommand.controller;

import com.rumahorbo.todocommand.model.Todo;
import com.rumahorbo.todocommand.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public Flux<Todo> getTodos() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Todo>> getTodo(@PathVariable Integer id) {
        return this.service.getById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Todo> postTodo(@RequestBody Todo todo) {
        return this.service.save(todo);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Todo>> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        return this.service.updateById(id, todo)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTodo(@PathVariable Integer id) {
        return this.service.deleteById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
