package com.rumahorbo.todocommand.service;

import com.rumahorbo.todocommand.model.Todo;
import com.rumahorbo.todocommand.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Mono<Void> deleteById(Integer id) {
        return this.repository.deleteById(id);
    }

    public Flux<Todo> getAll() {
        return this.repository.findAll();
    }

    public Mono<Todo> getById(Integer id) {
        return this.repository.findById(id);
    }

    public Mono<Todo> save(Todo todo) {
        return this.repository.save(todo);
    }

    public Mono<Todo> updateById(Integer id, Todo todo) {
        return this.repository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTodo -> this.update(optionalTodo, todo, id));
    }

    private Mono<Todo> update(Optional<Todo> optionalTodo, Todo todo, Integer id) {
        if (optionalTodo.isPresent()) {
            todo.setId(id);
            return this.repository.save(todo);
        }
        return Mono.empty();
    }
}
