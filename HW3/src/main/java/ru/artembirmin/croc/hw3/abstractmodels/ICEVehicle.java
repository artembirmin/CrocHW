package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * ICE - Internal Combustion Engine
 */
public abstract class ICEVehicle extends WheeledVehicle implements CanDrive {
    protected int horsepower;
    protected int maxSpeed;
    protected int fuelConsumption;
    protected boolean canDrive = true;

    public ICEVehicle(int serialNumber, String manufacturer, String model,
                      int seatsCount, String productionYear, int wheelsCount,
                      int horsepower, int maxSpeed, int fuelConsumption) {
        super(serialNumber, manufacturer, model, seatsCount, productionYear, wheelsCount);
        this.horsepower = horsepower;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
    }

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
