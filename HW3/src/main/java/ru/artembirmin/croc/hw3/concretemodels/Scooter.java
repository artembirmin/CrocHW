package ru.artembirmin.croc.hw3.concretemodels;

import ru.artembirmin.croc.hw3.abstractmodels.PersonalMobilityVehicle;

/**
 * Самокат.
 */
public class Scooter extends PersonalMobilityVehicle {

    /**
     * @param serialNumber   Серийный номер.
     * @param manufacturer   Производитель.
     * @param model          Модель.
     * @param seatsCount     Количество мест для размещения людей.
     * @param productionYear Год/Дата производства.
     * @param wheelsCount    Количество колес.
     * @param weight         Вес.
     */
    public Scooter(int serialNumber, String manufacturer, String model,
                   int seatsCount, String productionYear, int wheelsCount,
                   int weight) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear, wheelsCount,
                weight);
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "canDrive=" + canDrive +
                ", weight=" + weight +
                ", wheelsCount=" + wheelsCount +
                ", serialNumber=" + serialNumber +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", seatsCount=" + seatsCount +
                '}';
    }
}
