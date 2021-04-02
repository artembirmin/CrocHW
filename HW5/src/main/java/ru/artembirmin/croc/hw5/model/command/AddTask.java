package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.service.TaskService;

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
    public boolean execute(TaskService taskService) {
        return taskService.appendTask(new Task(
                taskService.generateId(),
                taskService.getTaskMetadataConsoleReader().readName(),
                taskService.getTaskMetadataConsoleReader().readDescription(),
                taskService.getExecutor(),
                taskService.getTaskMetadataConsoleReader().readStatus()
        ));
    }
}
