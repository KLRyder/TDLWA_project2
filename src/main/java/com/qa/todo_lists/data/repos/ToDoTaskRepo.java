package com.qa.todo_lists.data.repos;

import com.qa.todo_lists.data.model.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoTaskRepo extends JpaRepository<ToDoTask,Long> {
}
