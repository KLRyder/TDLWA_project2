package com.qa.todo_lists.controller;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.service.ToDoTaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(ToDoTaskController.class)
public class ToDoTaskControllerUnitTest {

    @Autowired
    private ToDoTaskController controller;

    @MockBean
    private ToDoTaskService service;

    private List<ToDoTaskDTO> taskDTOs;

    private ToDoTask task;
    private ToDoTaskDTO taskDTO;

    private TaskList dummyList = new TaskList();
    private Date dummyDate = new Date();

    @BeforeEach
    public void init() {
        task = new ToDoTask(1L, dummyList, "desc", dummyDate, false);
        taskDTO = new ToDoTaskDTO(1L, "desc", dummyDate, false);

        taskDTOs = new ArrayList<>();

        taskDTOs.add(taskDTO);
    }

    @Test
    public void getAllTasksTest() {
        when(service.readAll()).thenReturn(taskDTOs);
        ResponseEntity<List<ToDoTaskDTO>> expectedReturn = new ResponseEntity<>(taskDTOs, HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.get(Optional.empty()));

        verify(service, times(1)).readAll();
    }

    @Test
    public void getTaskByIdTest() {
        when(service.readById(1L)).thenReturn(taskDTO);
        ResponseEntity<List<ToDoTaskDTO>> expectedReturn = new ResponseEntity<>(taskDTOs, HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.get(Optional.of(1L)));

        verify(service, times(1)).readById(1L);
    }

    @Test
    public void createTaskTest() {
        when(service.create(task)).thenReturn(taskDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "1");
        ResponseEntity<ToDoTaskDTO> expectedReturn = new ResponseEntity<>(taskDTO, headers, HttpStatus.CREATED);

        assertThat(expectedReturn).isEqualTo(controller.post(task));

        verify(service, times(1)).create(task);
    }

    @Test
    public void deleteTaskTest() {
        when(service.delete(1L)).thenReturn(true);

        ResponseEntity<String> expectedReturn = new ResponseEntity<>("Task 1 deleted successfully.", HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.delete(1L));

        verify(service, times(1)).delete(1L);
    }

    @Test
    public void updateTaskTest() {
        when(service.update(task)).thenReturn(taskDTO);

        ResponseEntity<ToDoTaskDTO> expectedReturn = new ResponseEntity<>(taskDTO, HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.update(task));

        verify(service, times(1)).update(task);
    }
}
