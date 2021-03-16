package com.qa.todo_lists.data.repos;

import com.qa.todo_lists.data.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepo extends JpaRepository<TaskList,Long> {
}
