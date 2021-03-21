package com.qa.todo_lists.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TaskListControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ToDoTaskDTO> taskDTOs;
    private List<TaskListDTO> taskListDTOS;

    @BeforeEach
    public void setup() {
        TaskList exampleList = new TaskList(1L, "testList");
        ToDoTask exampleTask = new ToDoTask(1L, exampleList, "testTask", new Date(946684800000L), false);
        ToDoTaskDTO exampleTaskDTO = new ToDoTaskDTO(1L, "testTask", new Date(946684800000L), false);

        exampleList.setTasks(List.of(exampleTask));
        TaskListDTO exampleTaskListDTO = new TaskListDTO(1L, "testList", List.of(exampleTaskDTO));
        List<ToDoTask> tasks = List.of(exampleTask);
        taskDTOs = List.of(exampleTaskDTO);
        taskListDTOS = List.of(exampleTaskListDTO);
    }

    @Test
    void createTest() throws Exception {
        TaskList toSave = new TaskList("toSave");
        TaskListDTO expectedTaskList = new TaskListDTO(2L,"toSave", new ArrayList<>());

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.request(HttpMethod.POST, "/lists");

        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(toSave));

        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(expectedTaskList));

        ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");

        mvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher)
                .andExpect(headerMatcher);
    }

    @Test
    void getAllTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.request(HttpMethod.GET, "/lists");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(taskListDTOS));

        mvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void getOneTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.request(HttpMethod.GET, "/lists?id=1");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(taskListDTOS));

        mvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void updateTest() throws Exception {
        TaskList updated = new TaskList(1L,"updated");
        TaskListDTO expectedTaskList = new TaskListDTO(1L,"updated", taskDTOs);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.request(HttpMethod.PUT, "/lists");

        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(updated));

        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(expectedTaskList));

        mvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void deleteTest() throws Exception{
        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.request(HttpMethod.DELETE, "/lists?id=1");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("Task list 1 deleted successfully.");

        mvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }
}
