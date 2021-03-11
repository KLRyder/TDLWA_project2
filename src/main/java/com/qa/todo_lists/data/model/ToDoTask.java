package com.qa.todo_lists.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ToDoTask {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
