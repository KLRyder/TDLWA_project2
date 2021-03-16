package com.qa.todo_lists.controller;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/lists")
@CrossOrigin
public class TaskListController {
    private TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<TaskListDTO> post(@RequestBody @Valid TaskList taskList) {
        TaskListDTO newTask = taskListService.create(taskList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", newTask.getId().toString());

        return new ResponseEntity<>(newTask, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskListDTO>> get(@RequestParam Optional<Long> id) {
        return null;
    }

    @PatchMapping
    public ResponseEntity<TaskListDTO> update(@RequestBody @Valid TaskList taskList) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        return null;
    }
}
