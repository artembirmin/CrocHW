package ru.artembirmin.croc.hw3.managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw3.abstractmodels.Vehicle;
import ru.artembirmin.croc.hw3.concretemodels.Bike;
import ru.artembirmin.croc.hw3.concretemodels.Car;
import ru.artembirmin.croc.hw3.concretemodels.Plain;

import static org.junit.jupiter.api.Assertions.*;

class RentManagerTest {

    private Bike bike;
    private Car car;
    private Plain plain;
    private RentManager rentManager;

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
        rentManager = new RentManager();
    }

    @Test
    void toRent() {
        //Сдали в аренду
        assertTrue(rentManager.toRent(bike));
        assertTrue(rentManager.toRent(car));
        assertTrue(rentManager.toRent(plain));
        //Сняли с аренды
        rentManager.takeOfRent(bike);
        rentManager.takeOfRent(car);
        rentManager.takeOfRent(plain);
        //Сломали
        bike.breakDown();
        car.breakDown();
        plain.breakDown();
        //Пытаемся снова сдать
        assertFalse(rentManager.toRent(bike));
        assertFalse(rentManager.toRent(car));
        assertFalse(rentManager.toRent(plain));
    }

    @Test
    void isRent() {
        //По умолчанию транспорт не в арнеде
        assertFalse(rentManager.isRent(bike));
        assertFalse(rentManager.isRent(car));
        assertFalse(rentManager.isRent(plain));
        //Сдали в аренду
        rentManager.toRent(bike);
        rentManager.toRent(car);
        rentManager.toRent(plain);
        //Должен быть в аренде
        assertTrue(rentManager.isRent(bike));
        assertTrue(rentManager.isRent(car));
        assertTrue(rentManager.isRent(plain));

    }

    @Test
    void takeOfRent() {
        //Сдали в арнеду
        rentManager.toRent(bike);
        rentManager.toRent(car);
        rentManager.toRent(plain);
        //Сняли с аренды
        rentManager.takeOfRent(bike);
        rentManager.takeOfRent(car);
        rentManager.takeOfRent(plain);
        //Не должн быть в аренде
        assertFalse(rentManager.isRent(bike));
        assertFalse(rentManager.isRent(car));
        assertFalse(rentManager.isRent(plain));
    }
}