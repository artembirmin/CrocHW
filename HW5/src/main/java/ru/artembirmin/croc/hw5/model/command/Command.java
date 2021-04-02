package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.service.TaskService;

/**
 * Команды для проведения операций над задачами.
 */
public abstract class Command {
    /**
     * Код команды.
     */
    private final String code;
    /**
     * Краткое описание команды.
     */
    private final String transcription;

    /**
     * @param code          код
     * @param transcription краткое описание
     */
    public Command(String code, String transcription) {
        this.code = code;
        this.transcription = transcription;
    }

    /**
     * Выполняет операцию.
     *
     * @param taskService сервис для работы с задачами
     * @return {@code true}, если операция выполнена успешно
     */
    public abstract boolean execute(TaskService taskService);

    public String getCode() {
        return code;
    }

    public String getTranscription() {
        return transcription;
    }
}
