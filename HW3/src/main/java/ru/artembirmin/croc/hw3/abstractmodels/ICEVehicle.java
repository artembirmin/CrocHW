package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * ICE - Internal Combustion Engine.
 * Транспортное средство с двигателем внутреннего сгорания
 */
public abstract class ICEVehicle extends WheeledVehicle implements CanDrive {
    /**
     * Количество лошадиных сил.
     */
    protected int horsepower;

    /**
     * Максимальная скорость.
     */
    protected int maxSpeed;

    /**
     * Расход топлива.
     */
    protected int fuelConsumption;

    /**
     * Может ли ехать.
     */
    protected boolean canDrive = true;

    public ICEVehicle(int serialNumber, String manufacturer, String model,
                      int seatsCount, String productionYear, int wheelsCount,
                      int horsepower, int maxSpeed, int fuelConsumption) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear, wheelsCount);
        this.horsepower = horsepower;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public boolean canDrive() {
        return canDrive;
    }

    @Override
    public boolean isServiceability() {
        return canDrive;
    }

    @Override
    public void repair() {
        canDrive = true;
    }

    @Override
    public void breakDown() {
        canDrive = false;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }
}
