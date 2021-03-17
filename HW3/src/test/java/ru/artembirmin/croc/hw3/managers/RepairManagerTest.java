package ru.artembirmin.croc.hw3.managers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw3.concretemodels.Bike;
import ru.artembirmin.croc.hw3.concretemodels.Car;
import ru.artembirmin.croc.hw3.concretemodels.Plain;

import static org.junit.jupiter.api.Assertions.*;

class RepairManagerTest {

    private Bike bike;
    private Car car;
    private Plain plain;
    RepairManager repairManager;

    @BeforeEach
    void setUp() {
        bike = new Bike(1337, "GT", "Zaskar",
                1,"2020", 2,14);
        car = new Car(1488, "LADA", "2107",
                4, "2007",4,
                300,240,15);
        plain = new Plain(228, "Boeing", "737",
                189,"2010", 12500,
                945);
        repairManager = new RepairManager();
    }

    @Test
    void repair() {
        bike.breakDown();
        car.breakDown();
        plain.breakDown();

        assertFalse(bike.isServiceability());
        assertFalse(car.isServiceability());
        assertFalse(plain.isServiceability());

        repairManager.repair(bike);
        repairManager.repair(car);
        repairManager.repair(plain);

        assertTrue(bike.isServiceability());
        assertTrue(car.isServiceability());
        assertTrue(plain.isServiceability());
    }
}