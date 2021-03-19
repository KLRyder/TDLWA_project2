package com.qa.todo_lists.service;

import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.exceptions.TaskNotFoundException;
import com.qa.todo_lists.mappers.ToDoTaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ToDoTaskServiceIntegrationTest {
    @Autowired
    private ToDoTaskService service;

    @Autowired
    private ToDoTaskMapper mapper;


    private TaskList testList;

    private ToDoTaskDTO testTaskDTO;

    @BeforeEach
    public void init() {
        testList = new TaskList(1L, "testList");
        ToDoTask testTask = new ToDoTask(1L, testList, "testTask", new Date(946684800000L), false);
        testList.setTasks(List.of(testTask));
        testTaskDTO = new ToDoTaskDTO(1L, "testTask", new Date(946684800000L), false);
    }

    @Test
    public void create() {
        Date dummyDate = new Date();
        ToDoTask newTask = new ToDoTask(testList, "newlist", dummyDate, true);
        ToDoTaskDTO expected = mapper.mapToDTO(newTask);
        ToDoTaskDTO returned = service.create(newTask);
        expected.setId(returned.getId());
        assertEquals(expected, returned);
    }

    @Test
    public void readAll() {
        assertEquals(List.of(testTaskDTO), service.readAll());
    }

    @Test
    public void readOne() {
        assertEquals(testTaskDTO, service.readById(1L));
    }

    @Test
    public void update() {
        ToDoTask updated = new ToDoTask(1L, testList, "updated", null, true);
        ToDoTaskDTO expectedTaskList = new ToDoTaskDTO(1L, "updated", null, true);

        assertEquals(expectedTaskList, service.update(updated));
    }

    @Test
    public void delete() {
        assertTrue(service.delete(1L));
        assertThrows(TaskNotFoundException.class, () -> service.delete(99999L));
    }
}
