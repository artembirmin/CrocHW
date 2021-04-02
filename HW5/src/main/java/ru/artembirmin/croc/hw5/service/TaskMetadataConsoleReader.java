package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.model.Status;

/**
 * Просит ввести и считывает метаданные для задач из консоли.
 */
public interface TaskMetadataConsoleReader {
    /**
     * Считывает название задачи.
     *
     * @return название задачи
     */
    String readName();

    /**
     * Считывает описание задачи.
     *
     * @return описание задачи
     */
    String readDescription();

    /**
     * Считывает статсус задачи.
     *
     * @return статус задачи
     */
    Status readStatus();

    /**
     * Считывает идентификатор задачи.
     *
     * @return идентификатор задачи
     */
    long readId();

    /**
     * Считывает номер поля для редактирования.
     *
     * @return номер поля для редактирования
     */
    String readFieldNumberForEdit();
}
