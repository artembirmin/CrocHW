package ru.artembirmin.croc.hw5.model.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.artembirmin.croc.hw5.model.Executor;
import ru.artembirmin.croc.hw5.model.Status;
import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReaderSubstitution;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест сохранения id при выполнении команд в последовательные запуски программы.
 * Запускать каждый метод отдельно в ручную.
 * FirstLaunchTest всегда серый. Результат покажет второй
 */
public class SecondLaunchTest {

    TaskMetadataConsoleReaderSubstitution taskMetadataConsoleReader = new TaskMetadataConsoleReaderSubstitution();
    File file = new File("D:\\tasks1.txt");
    Executor executor = new Executor("artembirmin", "Artem", "Petrosyan");
    TaskService taskService = new TaskService(
            executor,
            file
    );

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

    private void checkAllTasks(List<Task> expectedTaskList) {
        List<Task> actualTaskList = null;
        try {
            actualTaskList = taskService.readAllTasks();
        } catch (IOException | ClassNotFoundException e) {
            assertDoesNotThrow((Executable) e);
        }
        assertEquals(expectedTaskList, actualTaskList);
    }

    @Test
    public void addCommandFirstLaunchTest() throws IOException {
        taskService.clear();
        initTasks();
        checkAllTasks(Arrays.asList(
                new Task(0, "1", "111", executor, Status.NOTSTARTED),
                new Task(1, "2", "222", executor, Status.COMPLETED),
                new Task(2, "3", "333", executor, Status.NOSTATUS),
                new Task(3, "4", "444", executor, Status.INPROGRESS),
                new Task(4, "5", "555", executor, Status.COMPLETED)
        ));
        new SaveAndExit("1","ex").execute(taskService,taskMetadataConsoleReader);
    }

    @Test
    public void addCommandSecondLaunchTest() {
        initTasks();
        checkAllTasks(Arrays.asList(
                new Task(0, "1", "111", executor, Status.NOTSTARTED),
                new Task(1, "2", "222", executor, Status.COMPLETED),
                new Task(2, "3", "333", executor, Status.NOSTATUS),
                new Task(3, "4", "444", executor, Status.INPROGRESS),
                new Task(4, "5", "555", executor, Status.COMPLETED),
                new Task(5, "1", "111", executor, Status.NOTSTARTED),
                new Task(6, "2", "222", executor, Status.COMPLETED),
                new Task(7, "3", "333", executor, Status.NOSTATUS),
                new Task(8, "4", "444", executor, Status.INPROGRESS),
                new Task(9, "5", "555", executor, Status.COMPLETED)
        ));
    }

    @Test
    public void deleteCommandFirstLaunchTest() throws IOException {
        taskService.clear();
        initTasks();
        taskMetadataConsoleReader.setId(2);
        assertTrue(new DeleteTask("2", "del").execute(taskService, taskMetadataConsoleReader));

        checkAllTasks(Arrays.asList(
                new Task(0, "1", "111", executor, Status.NOTSTARTED),
                new Task(1, "2", "222", executor, Status.COMPLETED),
                new Task(3, "4", "444", executor, Status.INPROGRESS),
                new Task(4, "5", "555", executor, Status.COMPLETED)
        ));

        new SaveAndExit("1","ex").execute(taskService,taskMetadataConsoleReader);
    }

    @Test
    public void deleteCommandSecondLaunchTest() {
        initTasks();
        checkAllTasks(Arrays.asList(
                new Task(0, "1", "111", executor, Status.NOTSTARTED),
                new Task(1, "2", "222", executor, Status.COMPLETED),
                new Task(3, "4", "444", executor, Status.INPROGRESS),
                new Task(4, "5", "555", executor, Status.COMPLETED),
                new Task(5, "1", "111", executor, Status.NOTSTARTED),
                new Task(6, "2", "222", executor, Status.COMPLETED),
                new Task(7, "3", "333", executor, Status.NOSTATUS),
                new Task(8, "4", "444", executor, Status.INPROGRESS),
                new Task(9, "5", "555", executor, Status.COMPLETED)
        ));
    }
}
