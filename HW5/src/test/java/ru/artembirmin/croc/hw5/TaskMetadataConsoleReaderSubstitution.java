package ru.artembirmin.croc.hw5;

import ru.artembirmin.croc.hw5.model.Status;
import ru.artembirmin.croc.hw5.service.TaskMetadataConsoleReader;

/**
 * Реаизация {@link TaskMetadataConsoleReader} для тестирования.
 */
public class TaskMetadataConsoleReaderSubstitution implements TaskMetadataConsoleReader {

    private String name;
    private String description;
    private Status status;
    private long id;

    private String[] fieldNumberForEditCombination;
    private int fieldPosition;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String readName() {
        System.out.println(name);
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String readDescription() {
        System.out.println(description);
        return description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Status readStatus() {
        System.out.println(status);
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long readId() {
        System.out.println(id);
        return id;
    }

    public void setFieldNumberForEditCombination(String... fieldNumberForEditCombination) {
        this.fieldNumberForEditCombination = fieldNumberForEditCombination;
    }

    @Override
    public String readFieldNumberForEdit() {
        System.out.println(fieldNumberForEditCombination[fieldPosition++]);
        return fieldNumberForEditCombination[fieldPosition++];
    }
}
