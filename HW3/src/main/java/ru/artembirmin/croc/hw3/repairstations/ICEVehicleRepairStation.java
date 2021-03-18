package ru.artembirmin.croc.hw3.repairstations;

import ru.artembirmin.croc.hw3.abstractmodels.CanDrive;

/**
 * Ремонтная станция для техники с двигателем внутреннего сгорания.
 */
//см. AircraftRepairStation
public class ICEVehicleRepairStation {
    /**
     * Починить средство, умеющее ездить.
     */
    public void repair(CanDrive vehicle) {
        vehicle.repair();
    }
}
