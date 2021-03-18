package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Воздушное транспортное стредство.
 */
public abstract class Aircraft extends RentableRepairableVehicle implements CanFly {

    /**
     * Максимальная высота полета в метрах.
     */
    protected int maxFlightAltitude;
    /**
     * Максимаьная скорость.
     */
    protected int maxSpeed;
    /**
     * Может ли летать.
     */
    protected boolean canFly = true;

    public Aircraft(int serialNumber, String manufacturer, String model,
                    int seatsCount, String productionYear, int maxFlightAltitude,
                    int maxSpeed) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear);
        this.maxFlightAltitude = maxFlightAltitude;
        this.maxSpeed = maxSpeed;
    }

    @Override
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
