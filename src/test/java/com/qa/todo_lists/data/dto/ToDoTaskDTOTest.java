package com.qa.todo_lists.data.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ToDoTaskDTOTest {
    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(ToDoTaskDTO.class).verify();
    }
}
