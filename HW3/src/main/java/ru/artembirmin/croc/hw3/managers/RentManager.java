package ru.artembirmin.croc.hw3.managers;

import ru.artembirmin.croc.hw3.abstractmodels.Rentable;

/**
 * Менеджер аренды транспорта.
 * Сдает в аренду, снимает с ареныды, проверяет на арендованность в данный момент
 */
public class RentManager {
    /**
     * Проверяет, арендованность транспорта в данный момент.
     *
     * @return false - транспорт не арендован, true - арендован.
     */
    public boolean isRent(Rentable vehicle) {
        return vehicle.isRent();
    }

    /**
     * Сдать в аренду.
     */
    public boolean toRent(Rentable vehicle) {
        return vehicle.toRent();
    }

    /**
     * Снять с аренды.
     */
    public void takeOfRent(Rentable vehicle) {
        vehicle.takeOffRent();
    }
}
