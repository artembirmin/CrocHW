package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.io.IOException;

/**
 * Команда добавления задачи.
 */
public class AddTask extends Command {

    /**
     * @param code          код
     * @param transcription краткое описание
     */
    public AddTask(String code, String transcription) {
        super(code, transcription);
    }

    @Override
    public boolean execute(TaskService taskService, TaskMetadataConsoleReader consoleReader) {
        try {
            return taskService.appendTask(new Task(
                    taskService.generateId(),
                    consoleReader.readName(),
                    consoleReader.readDescription(),
                    taskService.getExecutor(),
                    consoleReader.readStatus()
            ));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
