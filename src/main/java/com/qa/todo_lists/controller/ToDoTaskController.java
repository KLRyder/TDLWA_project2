package com.qa.todo_lists.controller;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.service.ToDoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tasks")
@CrossOrigin
public class ToDoTaskController {

    private ToDoTaskService taskService;

    @Autowired
    public ToDoTaskController(ToDoTaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ToDoTaskDTO> postTask(@RequestBody @Valid ToDoTask task) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<ToDoTaskDTO>> getTasks(@RequestParam Optional<Long> id) {
        return null;
    }

    @PatchMapping
    public ResponseEntity<ToDoTaskDTO> updateTask(@RequestBody @Valid ToDoTask task) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTask(@RequestParam Long id) {
        return null;
    }
}
