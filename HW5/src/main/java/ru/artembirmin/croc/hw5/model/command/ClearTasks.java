package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.io.IOException;

public class ClearTasks extends Command {
    /**
     * @param code          код
     * @param transcription краткое описание
     */
    public ClearTasks(String code, String transcription) {
        super(code, transcription);
    }

    @Override
    public boolean execute(TaskService taskService, TaskMetadataConsoleReader consoleReader) {
        try {
            return taskService.clear();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
