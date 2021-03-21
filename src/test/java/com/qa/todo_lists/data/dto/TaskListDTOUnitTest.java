package com.qa.todo_lists.data.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class TaskListDTOUnitTest {
    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(TaskListDTO.class).verify();
    }
}
