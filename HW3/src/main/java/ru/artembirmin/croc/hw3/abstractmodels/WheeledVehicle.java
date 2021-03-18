package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Колесная техника.
 */
public abstract class WheeledVehicle extends RentableRepairableVehicle {

    /**
     * Количество колес.
     */
    protected int wheelsCount;

    public WheeledVehicle(int serialNumber, String manufacturer, String model,
                          int seatsCount, String productionYear, int wheelsCount) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear);
        this.wheelsCount = wheelsCount;
    }
}
