package ru.artembirmin.croc.hw3.concretemodels;

import ru.artembirmin.croc.hw3.abstractmodels.PersonalMobilityVehicle;

public class Scooter extends PersonalMobilityVehicle {


    public Scooter(int serialNumber, String manufacturer, String model,
                   int seatsCount, String productionYear, int wheelsCount,
                   int weight) {
        super(serialNumber, manufacturer, model, seatsCount, productionYear, wheelsCount, weight);
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
