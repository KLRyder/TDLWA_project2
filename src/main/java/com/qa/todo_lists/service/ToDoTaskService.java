package com.qa.todo_lists.service;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.data.repos.ToDoTaskRepo;
import com.qa.todo_lists.exceptions.TaskNotFoundException;
import com.qa.todo_lists.mappers.ToDoTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoTaskService {

    private ToDoTaskRepo toDoTaskRepo;
    private ToDoTaskMapper toDoTaskMapper;

    @Autowired
    public ToDoTaskService(ToDoTaskRepo toDoTaskRepo, ToDoTaskMapper toDoTaskMapper) {
        this.toDoTaskRepo = toDoTaskRepo;
        this.toDoTaskMapper = toDoTaskMapper;
    }

    public ToDoTaskDTO createTask(ToDoTask task) {
        return null;
    }

    public List<ToDoTaskDTO> readAllTasks() {
        return null;
    }

    public ToDoTaskDTO readById(Long id) throws TaskNotFoundException {
        return null;
    }

    public ToDoTaskDTO updateTask(ToDoTask task) throws TaskNotFoundException{
        return null;
    }

    public boolean deleteTask(Long id) {
        return false;
    }
}
