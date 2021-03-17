package ru.artembirmin.croc.hw3.abstractmodels;

public abstract class Aircraft extends RentableVehicle implements CanFly {

    /**
     * In meters
     */
    protected int maxFlightAltitude;
    protected int maxSpeed;
    protected boolean canFly = true;

    public Aircraft(int serialNumber, String manufacturer, String model,
                    int seatsCount, String productionYear, int maxFlightAltitude, int maxSpeed) {
        super(serialNumber, manufacturer, model, seatsCount, productionYear);
        this.maxFlightAltitude = maxFlightAltitude;
        this.maxSpeed = maxSpeed;
    }

    public boolean canFly() {
        return canFly;
    }

    @Override
    public boolean isServiceability() {
        return canFly;
    }

    @Override
    public void repair() {
        canFly = true;
    }

    @Override
    public void breakDown() {
        canFly = false;
    }

    public int getMaxFlightAltitude() {
        return maxFlightAltitude;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
