package com.qa.todo_lists.data.dto;

import java.util.Date;

public class ToDoTaskDTO {
    private Long id;

    private String description;
    private Date dueDate;
    private Boolean complete;

    public ToDoTaskDTO() {
    }

    public ToDoTaskDTO(Long id, String description, Date dueDate, Boolean complete) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public ToDoTaskDTO(String description, Date dueDate, Boolean complete) {
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public ToDoTaskDTO(String description, Boolean complete) {
        this.description = description;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (this == o) return true;
        if (!(o instanceof ToDoTaskDTO)) return false;

        ToDoTaskDTO that = (ToDoTaskDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        return complete != null ? complete.equals(that.complete) : that.complete == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (complete != null ? complete.hashCode() : 0);
        return result;
    }
}
