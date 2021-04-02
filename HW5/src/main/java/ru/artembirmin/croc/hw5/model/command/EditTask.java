package ru.artembirmin.croc.hw5.model.command;

import ru.artembirmin.croc.hw5.model.Status;
import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;
import ru.artembirmin.croc.hw5.service.TaskService;

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
    public boolean execute(TaskService taskService) {
        TaskMetadataConsoleReader metadataConsoleReader = taskService.getTaskMetadataConsoleReader();
        long id = taskService.getTaskMetadataConsoleReader().readId();
        String field;
        while (true) {
            Task task = taskService.findById(id);
            if (task == null) {
                System.out.println("Не верный id.");
                return false;
            }
            field = metadataConsoleReader.readFieldNumberForEdit();
            switch (field) {
                case "1": {
                    String newName = metadataConsoleReader.readName();
                    task.setName(newName);
                    if (!taskService.replaceTask(task, id)) {
                        System.out.println("Замена не удалась");
                    }
                    break;
                }
                case "2": {
                    String newDescription = metadataConsoleReader.readDescription();
                    task.setDescription(newDescription);
                    taskService.replaceTask(task, id);
                    break;
                }
                case "3": {
                    Status newStatus = metadataConsoleReader.readStatus();
                    task.setStatus(newStatus);
                    taskService.replaceTask(task, id);
                    break;
                }
                default:
                    return true;
            }
        }
        //return true;
    }
}
