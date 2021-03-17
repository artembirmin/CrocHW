package ru.artembirmin.croc.hw3.abstractmodels;

public abstract class RentableVehicle extends Vehicle implements Rentable {

    private boolean isRent;

    public RentableVehicle(int serialNumber, String manufacturer, String model, int seatsCount, String productionYear) {
        super(serialNumber, manufacturer, model, seatsCount, productionYear);
    }

    protected abstract boolean isServiceability();

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