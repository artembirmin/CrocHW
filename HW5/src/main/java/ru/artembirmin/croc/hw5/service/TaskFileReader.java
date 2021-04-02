package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.model.exceptions.TaskNotFoundException;

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
     * @throws IOException            если файл не найден или какие-то доругие проблемы с файлом
     * @throws ClassNotFoundException в файле другой объект
     */
    public List<Task> readAllTasksFromFile(File file) throws IOException, ClassNotFoundException {
        List<Task> tasks = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                tasks.add((Task) objectInputStream.readObject());
            }
        } catch (EOFException e) {
            // Прочитали все задачи
        }
        return tasks;
    }

    /**
     * Находит и возвращает задачу по id.
     *
     * @param file файл, в котором производится поиск
     * @param id   идентификатор, по которому производится поиск
     * @return найденная задача или {@code null}, если задача с таким id не найдена
     * @throws IOException            если файл не найден или какие-то доругие проблемы с файлом
     * @throws ClassNotFoundException в файле другой объект
     * @throws TaskNotFoundException  задача не найдена в файле
     */
    public Task findById(File file, long id) throws IOException, ClassNotFoundException, TaskNotFoundException {
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
            throw new TaskNotFoundException("Task not found");
        }
    }
}
