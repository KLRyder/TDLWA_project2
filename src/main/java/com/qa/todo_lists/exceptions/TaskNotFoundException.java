package com.qa.todo_lists.exceptions;

import javax.persistence.EntityNotFoundException;

public class TaskNotFoundException extends EntityNotFoundException {
    public TaskNotFoundException() {
        super("the requested Task could not be found");
    }

    public TaskNotFoundException(String msg){
        super(msg);
    }
}
