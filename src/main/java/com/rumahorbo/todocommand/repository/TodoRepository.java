package com.rumahorbo.todocommand.repository;

import com.rumahorbo.todocommand.model.Todo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends ReactiveCrudRepository<Todo, Integer> {
}
