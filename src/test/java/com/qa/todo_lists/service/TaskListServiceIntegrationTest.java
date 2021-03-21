package com.qa.todo_lists.service;

import com.qa.todo_lists.data.dto.TaskListDTO;
import com.qa.todo_lists.data.dto.ToDoTaskDTO;
import com.qa.todo_lists.data.model.TaskList;
import com.qa.todo_lists.data.model.ToDoTask;
import com.qa.todo_lists.exceptions.TaskListNotFoundException;
import com.qa.todo_lists.mappers.TaskListMapper;
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
class TaskListServiceIntegrationTest {
    @Autowired
    private TaskListService service;

    @Autowired
    private TaskListMapper mapper;

    private List<TaskListDTO> listDTOs;

    private TaskListDTO testListDTO;

    private ToDoTaskDTO testTaskDTO;

    @BeforeEach
    public void init() {
        TaskList testList = new TaskList(1L, "testList");
        ToDoTask testTask = new ToDoTask(1L, testList, "testTask", new Date(946684800000L), false);
        testList.setTasks(List.of(testTask));
        testTaskDTO = new ToDoTaskDTO(1L, "testTask", new Date(946684800000L), false);
        testListDTO = new TaskListDTO(1L, "testList", List.of(testTaskDTO));
        listDTOs = List.of(testListDTO);
    }

    @Test
    void create(){
        TaskList newList = new TaskList("newlist");
        TaskListDTO expected = mapper.mapToDTO(newList);
        TaskListDTO returned = service.create(newList);
        expected.setId(returned.getId());
        assertEquals(expected, returned);
    }

    @Test
    void readAll(){
        assertEquals(listDTOs, service.readAll());
    }

    @Test
    void readOne(){
        assertEquals(testListDTO, service.readById(1L));
    }

    @Test
    void update(){
        TaskList updated = new TaskList(1L,"updated");
        TaskListDTO expectedTaskList = new TaskListDTO(1L,"updated", List.of(testTaskDTO));

        assertEquals(expectedTaskList, service.update(updated));
    }

    @Test
    void delete(){
        assertTrue(service.delete(1L));
        assertThrows(TaskListNotFoundException.class, () -> service.delete(99999L));
    }
}
