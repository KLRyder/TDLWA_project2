package com.qa.todo_lists.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity<String> taskNotFoundHandler(TaskNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TaskListNotFoundException.class)
    public ResponseEntity<String> taskListNotFoundHandler(TaskListNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
