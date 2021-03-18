package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Арендуемое и ремонтируемое транспортное срество.
 */
//Над названием можно еще подумать
public abstract class RentableRepairableVehicle extends Vehicle implements Rentable, Repairable {

    /**
     * Арендовано ли транспортное средство.
     */
    private boolean isRent = false;

    public RentableRepairableVehicle(int serialNumber, String manufacturer, String model,
                                     int seatsCount, String productionYear) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear);
    }

    @Override
    public boolean isRent() {
        return isRent;
    }

    @Override
    public boolean toRent() {
        if (isServiceability()) {
            isRent = true;
            return true;
        } else {
            isRent = false;
            return false;
        }
    }

    @Override
    public void takeOffRent() {
        isRent = false;
    }

}