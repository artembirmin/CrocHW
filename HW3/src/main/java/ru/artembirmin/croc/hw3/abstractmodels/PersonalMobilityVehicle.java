package ru.artembirmin.croc.hw3.abstractmodels;

public abstract class PersonalMobilityVehicle extends WheeledVehicle implements CanDrive {

    /**
     * In kg
     */
    protected int weight;
    protected boolean canDrive = true;

    public PersonalMobilityVehicle(int serialNumber, String manufacturer, String model,
                                   int seatsCount, String productionYear, int wheelsCount,
                                   int weight) {
        super(serialNumber, manufacturer, model, seatsCount, productionYear, wheelsCount);
        this.weight = weight;
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
}
