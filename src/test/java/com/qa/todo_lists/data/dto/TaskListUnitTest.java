package com.qa.todo_lists.data.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class TaskListUnitTest {
    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(TaskListDTO.class).verify();
    }
}
