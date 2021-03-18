package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Добавляет возможность починить и сломать транспортное средство.
 */
public interface Repairable {
    /**
     * Починить.
     */
    void repair();

    /**
     * Сломать.
     */
    void breakDown();

    /**
     * Проверка на исправость транспорта.
     *
     * @return false - не исправно, true - исправно.
     */
    boolean isServiceability();
}
