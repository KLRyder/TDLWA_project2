package com.qa.todo_lists.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class ToDoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @ManyToOne(targetEntity = TaskList.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list")
    @NotNull
    private TaskList taskList;

    @NotNull
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @NotNull
    private Boolean complete;

    public ToDoTask(Long id, @NotNull TaskList taskList, @NotNull String description, Date dueDate, @NotNull Boolean complete) {
        this.id = id;
        this.taskList = taskList;
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public ToDoTask(@NotNull TaskList taskList, @NotNull String description, Date dueDate, @NotNull Boolean complete) {
        this.taskList = taskList;
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public ToDoTask(@NotNull TaskList taskList, @NotNull String description, @NotNull Boolean complete) {
        this.taskList = taskList;
        this.description = description;
        this.complete = complete;
    }

    public ToDoTask() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)return false;
        if (this == o) return true;
        if (!(o instanceof ToDoTask)) return false;

        ToDoTask toDoTask = (ToDoTask) o;

        if (!taskList.equals(toDoTask.taskList)) return false;
        if (!description.equals(toDoTask.description)) return false;
        if (!Objects.equals(dueDate, toDoTask.dueDate)) return false;
        return complete.equals(toDoTask.complete);
    }

    @Override
    public int hashCode() {
        int result = taskList != null ? taskList.hashCode(): 0;
        result = 31 * result + description.hashCode();
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + complete.hashCode();
        return result;
    }
}
