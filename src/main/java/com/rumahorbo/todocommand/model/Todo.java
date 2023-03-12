package com.rumahorbo.todocommand.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table
@Data
public class Todo {

    @Id
    private Integer id;

    @Column
    private String title;

    @Column
    private Boolean isCompleted;

    public Todo(String title, Boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (!Objects.equals(id, todo.id)) return false;
        if (!Objects.equals(title, todo.title)) return false;
        return Objects.equals(isCompleted, todo.isCompleted);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isCompleted != null ? isCompleted.hashCode() : 0);
        return result;
    }
}
