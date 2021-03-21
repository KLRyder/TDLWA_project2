package com.qa.todo_lists.mappers;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class TaskListMapperUnitTest {
    @InjectMocks
    public TaskListMapper mapper;

    @Mock
    public ToDoTaskMapper taskMapper;

    public TaskList list;
    public TaskListDTO listDTO;
    public ToDoTask dummyTask;
    public ToDoTaskDTO dummyTaskDTO;

    @BeforeEach
    public void setupTests() {
        dummyTask = new ToDoTask();
        dummyTaskDTO = new ToDoTaskDTO();
        list = new TaskList(1L, "listName");
        list.setTasks(List.of(dummyTask));
        listDTO = new TaskListDTO(1L, "listName", List.of(dummyTaskDTO));
    }

    @Test
    void testMapToDTO(){
        when(taskMapper.mapToDTO(dummyTask)).thenReturn(dummyTaskDTO);

        assertEquals(listDTO, mapper.mapToDTO(list));

        verify(taskMapper, times(1)).mapToDTO(dummyTask);
    }

    @Test
    void testMapToDTOList(){
        when(taskMapper.mapToDTO(dummyTask)).thenReturn(dummyTaskDTO);
        assertEquals(List.of(listDTO), mapper.mapToDTO(List.of(list)));
        verify(taskMapper, times(1)).mapToDTO(dummyTask);
    }
}
