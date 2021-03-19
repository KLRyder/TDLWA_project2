package com.qa.todo_lists.controller;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.service.TaskListService;
import com.qa.todo_lists.service.ToDoTaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(TaskListController.class)
public class TaskListControllerUnitTest {

    @Autowired
    private TaskListController controller;

    @MockBean
    private TaskListService service;

    private List<TaskListDTO> taskListDTOs;

    private TaskList taskList;
    private TaskListDTO taskListDTO;

    private List<ToDoTask> dummyList;
    private List<ToDoTaskDTO> dummyDTOList;

    @BeforeEach
    public void init() {
        taskList = new TaskList(1L, "List name");
        taskListDTO = new TaskListDTO(1L, "List name", dummyDTOList);

        taskListDTOs = new ArrayList<>();

        taskListDTOs.add(taskListDTO);
    }

    @Test
    public void getAllListsTest() {
        when(service.readAll()).thenReturn(taskListDTOs);
        ResponseEntity<List<TaskListDTO>> expectedReturn = new ResponseEntity<>(taskListDTOs, HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.get(Optional.empty()));

        verify(service, times(1)).readAll();
    }

    @Test
    public void getListByIdTest() {
        when(service.readById(1L)).thenReturn(taskListDTO);
        ResponseEntity<List<TaskListDTO>> expectedReturn = new ResponseEntity<>(taskListDTOs, HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.get(Optional.of(1L)));

        verify(service, times(1)).readById(1L);
    }

    @Test
    public void createListTest() {
        when(service.create(taskList)).thenReturn(taskListDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "1");
        ResponseEntity<TaskListDTO> expectedReturn = new ResponseEntity<>(taskListDTO, headers, HttpStatus.CREATED);

        assertThat(expectedReturn).isEqualTo(controller.post(taskList));

        verify(service, times(1)).create(taskList);
    }

    @Test
    public void deleteListTest() {
        when(service.delete(1L)).thenReturn(true);

        ResponseEntity<String> expectedReturn = new ResponseEntity<>("Task list 1 deleted successfully.", HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.delete(1L));

        verify(service, times(1)).delete(1L);
    }

    @Test
    public void updateListTest() {
        when(service.update(taskList)).thenReturn(taskListDTO);

        ResponseEntity<TaskListDTO> expectedReturn = new ResponseEntity<>(taskListDTO, HttpStatus.OK);

        assertThat(expectedReturn).isEqualTo(controller.update(taskList));

        verify(service, times(1)).update(taskList);
    }
}