package com.qa.todo_lists.mappers;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskMapperUnitTest {
    public ToDoTask task;
    public ToDoTaskDTO taskDTO;
    public ToDoTaskMapper mapper;

    @BeforeEach
    public void setupTests() {
        TaskList dummyList = new TaskList();
        Date dummyDate = new Date();
        task = new ToDoTask(1L, dummyList, "taskDesc", dummyDate, false);
        taskDTO = new ToDoTaskDTO(1L, "taskDesc", dummyDate, false);
        mapper = new ToDoTaskMapper();
    }

    @Test
    public void testMapToDTO(){
        assertEquals(taskDTO, mapper.mapToDTO(task));
    }

    @Test
    public void testMapToDTOList(){
        assertEquals(List.of(taskDTO), mapper.mapToDTO(List.of(task)));
    }
}
