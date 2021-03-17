package ru.artembirmin.croc.hw3.concretemodels;

import ru.artembirmin.croc.hw3.abstractmodels.ICEVehicle;

public class Car extends ICEVehicle{

    public Car(int serialNumber, String manufacturer, String model,
               int seatsCount, String productionYear, int wheelsCount,
               int horsepower, int maxSpeed, int fuelConsumption) {
        super(serialNumber, manufacturer, model, seatsCount, productionYear, wheelsCount, horsepower, maxSpeed, fuelConsumption);
    }

    @Override
    public String toString() {
        return "Car{" +
                "canDrive=" + canDrive +
                ", horsepower=" + horsepower +
                ", maxSpeed=" + maxSpeed +
                ", fuelConsumption=" + fuelConsumption +
                ", wheelsCount=" + wheelsCount +
                ", serialNumber=" + serialNumber +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", seatsCount=" + seatsCount +
                '}';
    }
}
