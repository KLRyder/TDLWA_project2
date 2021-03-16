package com.qa.todo_lists.mappers;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.ToDoTask;
import org.springframework.stereotype.Component;

@Component
public class ToDoTaskMapper {
    public ToDoTaskDTO mapToDTO(ToDoTask toDoTask){
        return new ToDoTaskDTO(toDoTask.getId(),toDoTask.getDescription(),toDoTask.getDueDate(),toDoTask.getComplete());
    }
}
