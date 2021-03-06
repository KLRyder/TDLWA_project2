package com.qa.todo_lists.service;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.repos.TaskListRepo;
import com.qa.todo_lists.exceptions.TaskListNotFoundException;
import com.qa.todo_lists.mappers.TaskListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public TaskListDTO create(TaskList taskList) {
        return taskListMapper.mapToDTO(taskListRepo.save(taskList));
    }

    @Transactional
    public List<TaskListDTO> readAll() {
        return taskListMapper.mapToDTO(taskListRepo.findAll());
    }

    @Transactional
    public TaskListDTO readById(Long id) throws TaskListNotFoundException {
        return taskListMapper.mapToDTO(taskListRepo.findById(id).orElseThrow(TaskListNotFoundException::new));
    }

    public TaskListDTO update(TaskList task) throws TaskListNotFoundException {
        var updated = taskListRepo.findById(task.getId()).orElseThrow(TaskListNotFoundException::new);
        updated.setName(task.getName());
        taskListRepo.save(updated);
        return taskListMapper.mapToDTO(updated);
    }

    public boolean delete(Long id) throws TaskListNotFoundException{
        if(taskListRepo.findById(id).isEmpty()){
            throw new TaskListNotFoundException();
        }
        taskListRepo.deleteById(id);
        return taskListRepo.findById(id).isEmpty();
    }
}
