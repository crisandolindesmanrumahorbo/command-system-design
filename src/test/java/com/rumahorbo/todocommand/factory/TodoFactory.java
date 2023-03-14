package com.rumahorbo.todocommand.factory;

import com.rumahorbo.todocommand.model.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoFactory {

    public Todo constructTodo(String title) {
        return new Todo(title, false);
    }
}
