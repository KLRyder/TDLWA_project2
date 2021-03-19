package com.qa.todo_lists.data.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ToDoTaskTest {
    @Test
    public void testEquals() {
        TaskList prefabTask1 = new TaskList(1L, "one");
        prefabTask1.setTasks(new ArrayList<>());
        TaskList prefabTask2 = new TaskList(2L,"two");
        prefabTask2.setTasks(new ArrayList<>());
        EqualsVerifier.forClass(ToDoTask.class).withPrefabValues(TaskList.class, prefabTask1, prefabTask2)
                .verify();
    }
}
