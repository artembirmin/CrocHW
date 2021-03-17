package ru.artembirmin.croc.hw3.repairstations;

import ru.artembirmin.croc.hw3.abstractmodels.CanFly;

/**
 * Можно было бы сделать интерфес с дженериком, которые реализовывали бы станции, передавать все станции в менеджер массивом
 * и вызывать метод внутри try/catch, но мы это не проходили
 */
public class AircraftRepairStation {
    public void repair(CanFly vehicle) {
        vehicle.repair();
    }
}
