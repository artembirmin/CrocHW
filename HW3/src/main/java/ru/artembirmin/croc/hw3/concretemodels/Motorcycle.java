package ru.artembirmin.croc.hw3.concretemodels;

import ru.artembirmin.croc.hw3.abstractmodels.ICEVehicle;

/**
 * Мотоцикл.
 */
public class Motorcycle extends ICEVehicle {

    /**
     * @param serialNumber    Серийный номер.
     * @param manufacturer    Производитель.
     * @param model           Модель.
     * @param seatsCount      Количество мест для размещения людей.
     * @param productionYear  Год/Дата производства.
     * @param wheelsCount     Количество колес.
     * @param horsepower      Количество лошадиных сил.
     * @param maxSpeed        Максимальная скорсть.
     * @param fuelConsumption Расход топлива.
     */
    public Motorcycle(int serialNumber, String manufacturer, String model,
                      int seatsCount, String productionYear, int wheelsCount,
                      int horsepower, int maxSpeed, int fuelConsumption) {
        super(serialNumber, manufacturer, model,
                seatsCount, productionYear, wheelsCount,
                horsepower, maxSpeed, fuelConsumption);
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "canDrive=" + canDrive +
                ", horsepower=" + horsepower +
                ", maxSpeed=" + maxSpeed +
                ", fuelConsumption=" + fuelConsumption +
                ", wheelsCount=" + wheelsCount +
                ", serialNumber=" + serialNumber +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", seatsCount=" + seatsCount +
                '}';
    }
}
