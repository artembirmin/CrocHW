package ru.artembirmin.croc.hw3.abstractmodels;

public abstract class WheeledVehicle extends RentableVehicle {

    protected int wheelsCount;

    public WheeledVehicle(int serialNumber, String manufacturer, String model,
                          int seatsCount, String productionYear, int wheelsCount) {
        super(serialNumber, manufacturer, model, seatsCount, productionYear);
        this.wheelsCount = wheelsCount;
    }
}
