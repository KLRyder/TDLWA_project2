package com.qa.todo_lists.service;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.repos.TaskListRepo;
import com.qa.todo_lists.exceptions.TaskListNotFoundException;
import com.qa.todo_lists.mappers.TaskListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {
    private TaskListRepo taskListRepo;
    private TaskListMapper taskListMapper;

    @Autowired
    public TaskListService(TaskListRepo taskListRepo, TaskListMapper taskListMapper) {
        this.taskListRepo = taskListRepo;
        this.taskListMapper = taskListMapper;
    }

    public TaskListDTO create(TaskList task) {
        return null;
    }

    public List<TaskListDTO> readAll() {
        return null;
    }

    public TaskListDTO readById(Long id) throws TaskListNotFoundException {
        return null;
    }

    public TaskListDTO update(TaskList task) throws TaskListNotFoundException{
        return null;
    }

    public boolean delete(Long id) {
        return false;
    }
}
