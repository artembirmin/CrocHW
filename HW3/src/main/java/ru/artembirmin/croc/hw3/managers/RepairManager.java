package ru.artembirmin.croc.hw3.managers;

import ru.artembirmin.croc.hw3.abstractmodels.Aircraft;
import ru.artembirmin.croc.hw3.abstractmodels.ICEVehicle;
import ru.artembirmin.croc.hw3.abstractmodels.PersonalMobilityVehicle;
import ru.artembirmin.croc.hw3.repairstations.AircraftRepairStation;
import ru.artembirmin.croc.hw3.repairstations.ICEVehicleRepairStation;
import ru.artembirmin.croc.hw3.repairstations.PersonalMobilityVehicleRepairStation;

public class RepairManager {
    private AircraftRepairStation aircraftRepairStation = new AircraftRepairStation();
    private ICEVehicleRepairStation iceVehicleRepairStation = new ICEVehicleRepairStation();
    private PersonalMobilityVehicleRepairStation personalMobilityVehicleRepairStation =
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
