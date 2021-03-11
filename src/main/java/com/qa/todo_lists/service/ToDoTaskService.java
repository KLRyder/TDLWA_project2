package com.qa.todo_lists.service;

import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.data.repos.ToDoTaskRepo;
import com.qa.todo_lists.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoTaskService {

    private ToDoTaskRepo toDoTaskRepo;

    @Autowired
    public ToDoTaskService(ToDoTaskRepo toDoTaskRepo) {
        this.toDoTaskRepo = toDoTaskRepo;
    }

    public ToDoTask createTask(ToDoTask task) {
        return null;
    }

    public List<ToDoTask> readAllTasks() {
        return null;
    }

    public ToDoTask readById(Long id) throws TaskNotFoundException {
        return null;
    }

    public ToDoTask updateTask(ToDoTask task) throws TaskNotFoundException{
        return null;
    }

    public boolean deleteTask(Long id) {
        return false;
    }
}
