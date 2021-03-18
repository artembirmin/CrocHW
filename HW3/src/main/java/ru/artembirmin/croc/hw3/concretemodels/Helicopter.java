package ru.artembirmin.croc.hw3.concretemodels;

import ru.artembirmin.croc.hw3.abstractmodels.Aircraft;

/**
 * Вертолет.
 */
public class Helicopter extends Aircraft {

    public Helicopter(int serialNumber, String manufacturer, String model,
                      int seatsCount, String productionYear, int maxFlightAltitude,
                      int maxSpeed) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear, maxFlightAltitude,
                maxSpeed);
    }

    @Override
    public String toString() {
        return "Helicopter{" +
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
