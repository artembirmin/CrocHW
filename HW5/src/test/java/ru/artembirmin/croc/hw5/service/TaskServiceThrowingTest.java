package ru.artembirmin.croc.hw5.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw5.model.Executor;
import ru.artembirmin.croc.hw5.model.Status;
import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.model.command.AddTask;
import ru.artembirmin.croc.hw5.model.exceptions.TaskNotFoundException;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceThrowingTest {

    TaskMetadataConsoleReaderSubstitution taskMetadataConsoleReader = new TaskMetadataConsoleReaderSubstitution();
    File file = new File("D:\\tasks.txt");
    Executor executor = new Executor("artembirmin", "Artem", "Petrosyan");
    TaskService taskService = new TaskService(
            executor,
            file
    );

    @BeforeEach
    void setUp() throws IOException {
        taskService.clear();
    }

    @AfterEach
    void tearDown() throws IOException {
        taskService.clear();
    }

    private void initTasks() {
        taskMetadataConsoleReader.setName("1");
        taskMetadataConsoleReader.setDescription("111");
        taskMetadataConsoleReader.setStatus(Status.NOTSTARTED);
        assertTrue(new AddTask("1", "add").execute(taskService, taskMetadataConsoleReader));

        taskMetadataConsoleReader.setName("2");
        taskMetadataConsoleReader.setDescription("222");
        taskMetadataConsoleReader.setStatus(Status.COMPLETED);
        assertTrue(new AddTask("1", "add").execute(taskService, taskMetadataConsoleReader));

        taskMetadataConsoleReader.setName("3");
        taskMetadataConsoleReader.setDescription("333");
        taskMetadataConsoleReader.setStatus(Status.NOSTATUS);
        assertTrue(new AddTask("1", "add").execute(taskService, taskMetadataConsoleReader));

        taskMetadataConsoleReader.setName("4");
        taskMetadataConsoleReader.setDescription("444");
        taskMetadataConsoleReader.setStatus(Status.INPROGRESS);
        assertTrue(new AddTask("1", "add").execute(taskService, taskMetadataConsoleReader));

        taskMetadataConsoleReader.setName("5");
        taskMetadataConsoleReader.setDescription("555");
        taskMetadataConsoleReader.setStatus(Status.COMPLETED);
        assertTrue(new AddTask("1", "add").execute(taskService, taskMetadataConsoleReader));
    }

    @Test
    void appendTask() {
        initTasks();
        taskService.changeFile(new File("bla.bla"));
        assertThrows(IOException.class,() -> taskService.appendTask(new Task(0, "1", "111", executor, Status.NOTSTARTED)));
    }

    @Test
    void findById() {
        initTasks();
        assertThrows(TaskNotFoundException.class,() -> taskService.findById(345));
    }

}