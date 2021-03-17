package ru.artembirmin.croc.hw3.managers;

import ru.artembirmin.croc.hw3.abstractmodels.Rentable;

public class RentManager {

    public boolean toRent(Rentable vehicle){
        return vehicle.toRent();
    }
    public void takeOfRent(Rentable vehicle){
        vehicle.takeOffRent();
    }
    public boolean isRent(Rentable vehicle){
        return vehicle.isRent();
    }
}
