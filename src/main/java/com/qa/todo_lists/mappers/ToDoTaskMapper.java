package com.qa.todo_lists.mappers;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.ToDoTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToDoTaskMapper {
    public ToDoTaskDTO mapToDTO(ToDoTask toDoTask) {
        return new ToDoTaskDTO(toDoTask.getId(), toDoTask.getDescription(), toDoTask.getDueDate(), toDoTask.getComplete());
    }

    public List<ToDoTaskDTO> mapToDTO(List<ToDoTask> toDoTasks) {
        List<ToDoTaskDTO> toReturn = new ArrayList<>();
        for (var toDoTask : toDoTasks) {
            toReturn.add(mapToDTO(toDoTask));
        }
        return toReturn;
    }
}
