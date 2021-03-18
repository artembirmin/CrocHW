package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Транспортное средство индивидуальной мобильности.
 */
public abstract class PersonalMobilityVehicle extends WheeledVehicle implements CanDrive {

    /**
     * Вес в килограммах.
     */
    protected int weight;
    /**
     * Может ли ехать.
     */
    protected boolean canDrive = true;

    public PersonalMobilityVehicle(int serialNumber, String manufacturer, String model,
                                   int seatsCount, String productionYear, int wheelsCount,
                                   int weight) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear, wheelsCount);
        this.weight = weight;
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
}
