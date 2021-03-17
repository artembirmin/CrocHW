package ru.artembirmin.croc.hw3.abstractmodels;

public abstract class Vehicle {
    protected int serialNumber;
    protected String manufacturer;
    protected String model;
    protected String productionYear;
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
