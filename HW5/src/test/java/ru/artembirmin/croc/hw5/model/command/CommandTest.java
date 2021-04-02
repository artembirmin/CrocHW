package ru.artembirmin.croc.hw5.model.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw5.TaskMetadataConsoleReaderSubstitution;
import ru.artembirmin.croc.hw5.model.Executor;
import ru.artembirmin.croc.hw5.model.Status;
import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.model.command.AddTask;
import ru.artembirmin.croc.hw5.model.command.DeleteTask;
import ru.artembirmin.croc.hw5.model.command.EditTask;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.io.*;

public class CommandTest {
    TaskMetadataConsoleReaderSubstitution taskMetadataConsoleReader = new TaskMetadataConsoleReaderSubstitution();
    File file = new File("D:\\tasks.txt");
    TaskService taskService = new TaskService(
            new Executor("artembirmin", "Artem", "Petrosyan"),
            file,
            taskMetadataConsoleReader
    );

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void addCommandTest() throws IOException, ClassNotFoundException {
        taskMetadataConsoleReader.setName("FirstTask");
        taskMetadataConsoleReader.setDescription("FirstTaskDescription");
        taskMetadataConsoleReader.setStatus(Status.NOTSTARTED);
        System.out.println(new AddTask("1", "add").execute(taskService));

        taskMetadataConsoleReader.setName("SECONDTask");
        System.out.println(new AddTask("1", "add").execute(taskService));

        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                System.out.println((Task) objectInputStream.readObject());
            }
        } catch (EOFException ignored) {
            System.out.println("Vce");
        }
    }

    @Test
    public void deleteCommandTest() throws IOException, ClassNotFoundException {
        taskMetadataConsoleReader.setName("FirstTask");
        taskMetadataConsoleReader.setDescription("FirstTaskDescription");
        taskMetadataConsoleReader.setStatus(Status.NOTSTARTED);
        //System.out.println(new AddTask("1", "add").execute(taskService));

        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                System.out.println("Tasks : ");
                System.out.println((Task) objectInputStream.readObject());
            }
        } catch (EOFException ignored) {
            // Прочитали все задачи
        }

        taskMetadataConsoleReader.setId(317);
        System.out.println(new DeleteTask("2", "del").execute(taskService));

        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            System.out.println("Tasks : ");
            while (true) {
                System.out.println((Task) objectInputStream.readObject());
            }
        } catch (EOFException ignored) {
            // Прочитали все задачи
        }
    }

    @Test
    public void editCommandTest() throws IOException, ClassNotFoundException {
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                System.out.println((Task) objectInputStream.readObject());
            }
        } catch (EOFException ignored) {
            System.out.println("Vce");
        }

        taskMetadataConsoleReader.setId(29346);
        taskMetadataConsoleReader.setFieldNumberForEditCombination("1", "2", "3", "ex");
        taskMetadataConsoleReader.setName("EditedTask");
        taskMetadataConsoleReader.setDescription("EditedTaskDescr");
        taskMetadataConsoleReader.setStatus(Status.COMPLETED);

        System.out.println(new EditTask("3", "edit").execute(taskService));

        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                System.out.println((Task) objectInputStream.readObject());
            }
        } catch (EOFException ignored) {
            System.out.println("Vce");
        }
    }
}
