package com.qa.todo_lists.service;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.repos.TaskListRepo;
import com.qa.todo_lists.mappers.TaskListMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class TaskListServiceUnitTest {
    @InjectMocks
    private TaskListService service;

    @Mock
    private TaskListRepo repo;

    @Mock
    private TaskListMapper mapper;

    private List<TaskList> lists;
    private List<TaskListDTO> dtos;
    private TaskList list;
    private TaskListDTO listDTO;

    @BeforeEach
    public void setupForTest() {
        Date dummyDate = new Date();
        list = new TaskList(1L, "taskName");
        ToDoTaskDTO taskDTO = new ToDoTaskDTO(1L, "taskDesc", dummyDate, false);
        listDTO = new TaskListDTO(1L, "taskName", List.of(taskDTO));

        lists = List.of(list);
        dtos = List.of(listDTO);
    }

    @Test
    public void createTest() {
        when(repo.save(list)).thenReturn(list);
        when(mapper.mapToDTO(any(TaskList.class))).thenReturn(listDTO);

        assertThat(listDTO).isEqualTo(service.create(list));

        verify(repo, times(1)).save(list);
        verify(mapper, times(1)).mapToDTO(any(TaskList.class));
    }

    @Test
    public void deleteTest() {
        when(repo.findById(any(Long.class))).thenReturn(Optional.of(list)).thenReturn(Optional.empty());

        assertTrue(service.delete(1L));

        verify(repo, times(2)).findById(any(Long.class));
        verify(repo, times(1)).deleteById(any(Long.class));
    }

    @Test
    public void updateTest() {
        TaskList oldList = new TaskList(1L, "old");

        when(repo.findById(any(Long.class))).thenReturn(Optional.of(oldList));
        when(mapper.mapToDTO(any(TaskList.class))).thenReturn(listDTO);

        assertThat(listDTO).isEqualTo(service.update(list));

        verify(repo, times(1)).findById(any(Long.class));
        verify(repo, times(1)).save(any(TaskList.class));
        verify(mapper, times(1)).mapToDTO(any(TaskList.class));
    }

    @Test
    public void readOneTest() {
        when(repo.findById(any(Long.class))).thenReturn(Optional.of(list));
        when(mapper.mapToDTO(any(TaskList.class))).thenReturn(listDTO);

        assertThat(listDTO).isEqualTo(service.readById(1L));

        verify(repo, times(1)).findById(any(Long.class));
        verify(mapper, times(1)).mapToDTO(any(TaskList.class));
    }

    @Test
    public void readAllTest() {
        when(repo.findAll()).thenReturn(lists);
        when(mapper.mapToDTO(lists)).thenReturn(dtos);

        assertThat(dtos).isEqualTo(service.readAll());

        verify(repo, times(1)).findAll();
        verify(mapper, times(1)).mapToDTO(lists);
    }


}
