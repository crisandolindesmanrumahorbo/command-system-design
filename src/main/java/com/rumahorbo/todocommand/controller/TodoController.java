package com.rumahorbo.todocommand.controller;

import com.rumahorbo.todocommand.model.Todo;
import com.rumahorbo.todocommand.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Mono<Todo> getTodo(@PathVariable Integer id) {
        return this.service.getById(id);
    }

    @PostMapping
    public Mono<Todo> postTodo(@RequestBody Todo todo) {
        return this.service.save(todo);
    }

    @PutMapping("/{id}")
    public Mono<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        return this.service.updateById(id, todo);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTodo(@PathVariable Integer id) {
        return this.service.deleteById(id);
    }
}
