package com.qa.todo_lists.service;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.data.repos.ToDoTaskRepo;
import com.qa.todo_lists.mappers.ToDoTaskMapper;
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
public class ToDoTaskServiceUnitTest {
    @InjectMocks
    private ToDoTaskService service;

    @Mock
    private ToDoTaskRepo repo;

    @Mock
    private ToDoTaskMapper mapper;

    private List<ToDoTask> tasks;
    private List<ToDoTaskDTO> dtos;
    private ToDoTask task;
    private ToDoTaskDTO dto;

    @BeforeEach
    public void setupForTest() {
        Date dummyDate = new Date();
        TaskList dummyList = new TaskList();
        task = new ToDoTask(1L, dummyList, "taskDesc", dummyDate, false);
        dto = new ToDoTaskDTO(1L, "taskDesc", dummyDate, false);

        tasks = List.of(task);
        dtos = List.of(dto);
    }

    @Test
    public void createTest() {
        when(repo.save(task)).thenReturn(task);
        when(mapper.mapToDTO(any(ToDoTask.class))).thenReturn(dto);

        assertThat(dto).isEqualTo(service.create(task));

        verify(repo, times(1)).save(task);
        verify(mapper, times(1)).mapToDTO(any(ToDoTask.class));
    }

    @Test
    public void deleteTest() {
        when(repo.findById(any(Long.class))).thenReturn(Optional.of(task)).thenReturn(Optional.empty());

        assertTrue(service.delete(1L));

        verify(repo, times(2)).findById(any(Long.class));
        verify(repo, times(1)).deleteById(any(Long.class));
    }

    @Test
    public void updateTest() {
        ToDoTask oldTask = new ToDoTask(1L, new TaskList(), "old", new Date(), true);

        when(repo.findById(any(Long.class))).thenReturn(Optional.of(oldTask));
        when(mapper.mapToDTO(any(ToDoTask.class))).thenReturn(dto);

        assertThat(dto).isEqualTo(service.update(task));

        verify(repo, times(1)).findById(any(Long.class));
        verify(repo, times(1)).save(any(ToDoTask.class));
        verify(mapper, times(1)).mapToDTO(any(ToDoTask.class));
    }

    @Test
    public void readOneTest() {
        when(repo.findById(any(Long.class))).thenReturn(Optional.of(task));
        when(mapper.mapToDTO(any(ToDoTask.class))).thenReturn(dto);

        assertThat(dto).isEqualTo(service.readById(1L));

        verify(repo, times(1)).findById(any(Long.class));
        verify(mapper, times(1)).mapToDTO(any(ToDoTask.class));
    }

    @Test
    public void readAllTest() {
        when(repo.findAll()).thenReturn(tasks);
        when(mapper.mapToDTO(tasks)).thenReturn(dtos);

        assertThat(dtos).isEqualTo(service.readAll());

        verify(repo, times(1)).findAll();
        verify(mapper, times(1)).mapToDTO(tasks);
    }


}
