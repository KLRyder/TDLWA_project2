package com.qa.todo_lists.data.dto;

import com.qa.todo_lists.data.model.TaskList;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class TaskListDTO {
    private Long id;
    private String name;
    private List<ToDoTaskDTO> tasks;

    public TaskListDTO(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public TaskListDTO(@NotNull String name) {
        this.name = name;
    }

    public TaskListDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ToDoTaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<ToDoTaskDTO> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskListDTO taskList = (TaskListDTO) o;

        if (!Objects.equals(id, taskList.id)) return false;
        if (!Objects.equals(name, taskList.name)) return false;
        return Objects.equals(tasks, taskList.tasks);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }
}
