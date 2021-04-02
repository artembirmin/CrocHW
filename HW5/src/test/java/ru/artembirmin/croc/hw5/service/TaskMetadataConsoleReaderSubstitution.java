package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.model.Status;

/**
 * Реаизация {@link TaskMetadataConsoleReader} для тестирования.
 */
public class TaskMetadataConsoleReaderSubstitution implements TaskMetadataConsoleReader {

    /**
     * Идентификатор.
     */
    private long id;
    /**
     * Название.
     */
    private String name;
    /**
     * Описание.
     */
    private String description;
    /**
     * Статус.
     */
    private Status status;

    /**
     * Комбинация номеров полей при редактировании задачи.
     */
    private String[] fieldNumberForEditCombination;
    /**
     * Номер номера поля.
     */
    private int fieldPosition = 0;



    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String readName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String readDescription() {
        return description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Status readStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long readId() {
        return id;
    }

    public void setFieldNumberForEditCombination(String... fieldNumberForEditCombination) {
        this.fieldNumberForEditCombination = fieldNumberForEditCombination;
    }

    @Override
    public String readFieldNumberForEdit() {
        return fieldNumberForEditCombination[fieldPosition++];
    }
}
