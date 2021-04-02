package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Читает задачи из файла.
 */
public class TaskFileReader {
    /**
     * Читает и возвращает все задачи из файла.
     *
     * @param file Читаемый файл
     * @return список всех задач из файла
     */
    public List<Task> readAllTasksFromFile(File file) {
        List<Task> tasks = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                tasks.add((Task) objectInputStream.readObject());
            }
        } catch (EOFException e) {
            // Прочитали все задачи
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
        return tasks;
    }

    /**
     * Находит и возвращает задачу по id.
     *
     * @param file файл, в котором производится поиск
     * @param id   идентификатор, по которому производится поиск
     * @return найденная задача или {@code null}, если задача с таким id не найдена
     */
    public Task findById(File file, long id) {
        Task task;
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                task = (Task) objectInputStream.readObject();
                if (task.getId() == id) {
                    return task;
                }
            }
        } catch (EOFException e) {
            // Прочитали все задачи
            return null;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
