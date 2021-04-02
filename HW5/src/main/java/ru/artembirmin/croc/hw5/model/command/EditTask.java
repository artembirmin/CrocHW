package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.model.Status;
import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.model.exceptions.TaskNotFoundException;
import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.io.IOException;

/**
 * Команда редактирования задачи.
 */
public class EditTask extends Command {

    /**
     * @param code          код
     * @param transcription краткое описание
     */
    public EditTask(String code, String transcription) {
        super(code, transcription);
    }

    @Override
    public boolean execute(TaskService taskService, TaskMetadataConsoleReader consoleReader) {
        long id = consoleReader.readId();
        String field;
        while (true) {
            Task task = null;
            try {
                task = taskService.findById(id);
            } catch (IOException | ClassNotFoundException | TaskNotFoundException e) {
                System.out.println(e.getMessage());
            }
            if (task == null) {
                System.out.println("Не верный id.");
                return false;
            }
            field = consoleReader.readFieldNumberForEdit();
            switch (field) {
                case "1": {
                    String newName = consoleReader.readName();
                    task.setName(newName);
                    try {
                        taskService.replaceTask(task, id);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    break;
                }
                case "2": {
                    String newDescription = consoleReader.readDescription();
                    task.setDescription(newDescription);
                    try {
                        taskService.replaceTask(task, id);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    break;
                }
                case "3": {
                    Status newStatus = consoleReader.readStatus();
                    task.setStatus(newStatus);
                    try {
                        taskService.replaceTask(task, id);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    break;
                }
                default:
                    return true;
            }
        }
    }
}
