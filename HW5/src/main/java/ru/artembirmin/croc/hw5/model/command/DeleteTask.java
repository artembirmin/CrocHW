package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.service.TaskService;

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
    public boolean execute(TaskService taskService) {
        long id = taskService.getTaskMetadataConsoleReader().readId();
        return taskService.remove(id);
    }
}
