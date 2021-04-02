package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.util.List;

public class PrintAllTasks extends Command {
    /**
     * @param code          код
     * @param transcription краткое описание
     */
    public PrintAllTasks(String code, String transcription) {
        super(code, transcription);
    }

    @Override
    public boolean execute(TaskService taskService, TaskMetadataConsoleReader consoleReader) {
        List<Task> tasks = taskService.readAllTasks();
        if (tasks == null) {
            return false;
        }
        System.out.println(tasks);
        return true;
    }
}
