package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.model.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Записывает задачи в файл.
 */
public class TaskFileWriter {

    /**
     * Очищает файл.
     *
     * @param file очищаемый файл
     * @return {@code true}, если файл найден и очищен
     */
    public boolean clear(File file) throws IOException {
        new FileOutputStream(file).close();
        return true;
    }

    /**
     * Вставляет список задач в заданный файл.
     *
     * @param file  файл, в который производится вставка.
     * @param tasks вставляемый список задач
     * @return {@code true}, если файл найден и все задачи вставлены
     */
    public boolean setTasks(File file, List<Task> tasks) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        for (Task task : tasks) {
            outputStream.writeObject(task);
        }
        outputStream.close();
        return true;
    }
}
