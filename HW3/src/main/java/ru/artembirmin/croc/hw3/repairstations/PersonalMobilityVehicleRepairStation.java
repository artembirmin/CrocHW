package ru.artembirmin.croc.hw3.repairstations;

import ru.artembirmin.croc.hw3.abstractmodels.CanDrive;

/**
 * Ремонтная станция для средств индивидуальной мобильности.
 */
//см. AircraftRepairStation
public class PersonalMobilityVehicleRepairStation {
    /**
     * Починить средство, умеющее ездить.
     */
    public void repair(CanDrive vehicle) {
        vehicle.repair();
    }
}
