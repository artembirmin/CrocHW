package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Определяет возможность транспорта к езде в данный момент.
 */
public interface CanDrive extends Repairable {
    /**
     * Проверяет возможность к езде в данный момент
     *
     * @return false - не может, true - может.
     */
    boolean canDrive();
}
