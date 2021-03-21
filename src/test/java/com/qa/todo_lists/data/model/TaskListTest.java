package com.qa.todo_lists.data.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;


class TaskListTest {
    @Test
    void testEquals() {
        ToDoTask prefabTask1 = new ToDoTask(new TaskList(),"one",true);
        ToDoTask prefabTask2 = new ToDoTask(new TaskList(),"two",true);
        EqualsVerifier.forClass(TaskList.class).withPrefabValues(ToDoTask.class, prefabTask1, prefabTask2)
                .verify();
    }
}
