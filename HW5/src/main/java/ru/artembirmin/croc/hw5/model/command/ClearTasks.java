package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;
import ru.artembirmin.croc.hw5.service.TaskService;

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
        return taskService.clear();
    }
}
