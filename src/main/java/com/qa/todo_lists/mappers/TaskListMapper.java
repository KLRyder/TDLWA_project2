package com.qa.todo_lists.mappers;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.model.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TaskListMapper {

    private ToDoTaskMapper toDoTaskMapper;

    @Autowired
    public TaskListMapper(ToDoTaskMapper toDoTaskMapper) {
        this.toDoTaskMapper = toDoTaskMapper;
    }

    public TaskListDTO mapToDTO(TaskList taskList) {
        return new TaskListDTO(taskList.getId(),
                taskList.getName(),
                taskList.getTasks().stream().map(
                        toDoTask -> toDoTaskMapper.mapToDTO(toDoTask)).collect(Collectors.toList()));
    }
}
