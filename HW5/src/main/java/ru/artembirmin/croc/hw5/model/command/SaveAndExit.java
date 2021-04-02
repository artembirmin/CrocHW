package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.service.TaskService;

/**
 * Команда выхода из программы.
 */
public class SaveAndExit extends Command {

    /**
     * @param code          код
     * @param transcription краткое описание
     */
    public SaveAndExit(String code, String transcription) {
        super(code, transcription);
    }

    @Override
    public boolean execute(TaskService taskService) {
        System.exit(0);
        return true;
    }
}
