package com.qa.todo_lists.controller;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.exceptions.TaskNotFoundException;
import com.qa.todo_lists.service.ToDoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ToDoTaskDTO> post(@RequestBody @Valid ToDoTask task) {
        ToDoTaskDTO newTask = taskService.create(task);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", newTask.getId().toString());

        return new ResponseEntity<>(newTask, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ToDoTaskDTO>> get(@RequestParam Optional<Long> id) {
        List<ToDoTaskDTO> tasks = id.map(aLong -> List.of(taskService.readById(aLong)))
                .orElseGet(() -> taskService.readAll());

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ToDoTaskDTO> update(@RequestBody @Valid ToDoTask task) {
        ToDoTaskDTO updatedTask = taskService.update(task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) throws TaskNotFoundException {
        if (taskService.delete(id)) {
            return new ResponseEntity<>("Task " + id + " deleted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't delete task " + id +
                ", task was found but not removed.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
