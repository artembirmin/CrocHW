package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Добавляет функциональность сдачи в аренду.
 */
public interface Rentable {

    /**
     * Проверяет, арендованность транспорта в данный момент
     *
     * @return false - транспорт не арендован, true - арендован.
     */
    boolean isRent();

    /**
     * Сдать в аренду.
     */
    boolean toRent();

    /**
     * Снять с аренды.
     */
    void takeOffRent();

}
