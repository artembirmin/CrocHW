package ru.artembirmin.croc.hw3.managers;

import ru.artembirmin.croc.hw3.abstractmodels.Aircraft;
import ru.artembirmin.croc.hw3.abstractmodels.ICEVehicle;
import ru.artembirmin.croc.hw3.abstractmodels.PersonalMobilityVehicle;
import ru.artembirmin.croc.hw3.repairstations.AircraftRepairStation;
import ru.artembirmin.croc.hw3.repairstations.ICEVehicleRepairStation;
import ru.artembirmin.croc.hw3.repairstations.PersonalMobilityVehicleRepairStation;

/**
 * Менеджер ремонта транспорта.
 * Ремонтирует следующие виды техники: воздушную, индивидуальной мобильности, с двигателем внутреннего сгорания.
 */
public class RepairManager {
    private final AircraftRepairStation aircraftRepairStation = new AircraftRepairStation();
    private final ICEVehicleRepairStation iceVehicleRepairStation = new ICEVehicleRepairStation();
    private final PersonalMobilityVehicleRepairStation personalMobilityVehicleRepairStation =
            new PersonalMobilityVehicleRepairStation();

    public void repair(Aircraft aircraft) {
        aircraftRepairStation.repair(aircraft);
    }

    public void repair(PersonalMobilityVehicle vehicle) {
        personalMobilityVehicleRepairStation.repair(vehicle);
    }

    public void repair(ICEVehicle vehicle) {
        iceVehicleRepairStation.repair(vehicle);
    }
}
