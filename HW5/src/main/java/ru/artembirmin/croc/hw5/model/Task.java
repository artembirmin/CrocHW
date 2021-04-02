package ru.artembirmin.croc.hw5.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Задача.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Идентификатор.
     */
    private final long id;
    /**
     * Название.
     */
    private String name;
    /**
     * Описание.
     */
    private String description;
    /**
     * Исполнитель.
     */
    private Executor executor;
    /**
     * Статус.
     */
    private Status status;

    /**
     * @param id          Идентификатор
     * @param name        Название
     * @param description Описание
     * @param executor    Исполнитель
     * @param status      Статус
     */
    public Task(long id, String name, String description, Executor executor, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getId() == task.getId() &&
                Objects.equals(getName(), task.getName()) &&
                Objects.equals(getDescription(), task.getDescription()) &&
                Objects.equals(getExecutor(), task.getExecutor()) &&
                getStatus() == task.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getExecutor(), getStatus());
    }

    @Override
    public String toString() {
        return "Task : \n" +
                "\tid - " + id + '\n' +
                "\tname - " + name + '\n' +
                "\tdescription - " + description + '\n' +
                "\texecutor : \n" + executor +
                "\tstatus - " + status;
    }
}
