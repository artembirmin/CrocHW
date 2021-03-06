package ru.artembirmin.croc.hw3.concretemodels;

import ru.artembirmin.croc.hw3.abstractmodels.Aircraft;

/**
 * Самолет.
 */
public class Plain extends Aircraft {

    /**
     * @param serialNumber      Серийный номер.
     * @param manufacturer      Производитель.
     * @param model             Модель.
     * @param seatsCount        Количество мест для размещения людей.
     * @param productionYear    Год/Дата производства.
     * @param maxFlightAltitude Максимальная высота полета
     * @param maxSpeed          Максимальная скорость
     */
    public Plain(int serialNumber, String manufacturer, String model,
                 int seatsCount, String productionYear, int maxFlightAltitude,
                 int maxSpeed) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear, maxFlightAltitude,
                maxSpeed);
    }


    @Override
    public String toString() {
        return "Plain{" +
                "canFly=" + canFly +
                ", maxFlightAltitude=" + maxFlightAltitude +
                ", maxSpeed=" + maxSpeed +
                ", serialNumber=" + serialNumber +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", seatsCount=" + seatsCount +
                '}';
    }

}
