package com.qa.todo_lists.exceptions;

import javax.persistence.EntityNotFoundException;

public class TaskListNotFoundException extends EntityNotFoundException {
    public TaskListNotFoundException() {
        super("the requested Task List could not be found");
    }

    public TaskListNotFoundException(String msg){
        super(msg);
    }
}
