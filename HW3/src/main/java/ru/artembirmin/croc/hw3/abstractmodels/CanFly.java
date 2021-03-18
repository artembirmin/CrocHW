package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Определяет возможность транспорта к полету в данный момент.
 */
public interface CanFly extends Repairable {
    /**
     * Проверяет возможность к полету в данный момент
     *
     * @return false - не может. true - может
     */
    boolean canFly();
}
