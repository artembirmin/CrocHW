package ru.artembirmin.croc.hw3.abstractmodels;

/**
 * Транспортное средство (транспорт).
 */
public abstract class Vehicle {

    /**
     * Серийный номер.
     */
    protected int serialNumber;

    /**
     * Производитель.
     */
    protected String manufacturer;

    /**
     * Модель.
     */
    protected String model;

    /**
     * Год/Дата производства.
     */
    protected String productionYear;

    /**
     * Количество мест для размещения людей.
     */
    protected int seatsCount;

    public Vehicle(int serialNumber, String manufacturer, String model,
                   int seatsCount, String productionYear) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.seatsCount = seatsCount;
        this.productionYear = productionYear;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
