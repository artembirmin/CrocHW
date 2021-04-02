package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.model.exceptions.TaskNotFoundException;
import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.io.IOException;

/**
 * Команда удаления задачи.
 */
public class DeleteTask extends Command {

    /**
     * @param code          код
     * @param transcription краткое описание
     */
    public DeleteTask(String code, String transcription) {
        super(code, transcription);
    }

    @Override
    public boolean execute(TaskService taskService, TaskMetadataConsoleReader consoleReader) {
        long id = consoleReader.readId();
        try {
            return taskService.remove(id);
        } catch (IOException | ClassNotFoundException | TaskNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
