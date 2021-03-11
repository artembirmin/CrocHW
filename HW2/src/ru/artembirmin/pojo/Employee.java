package ru.artembirmin.pojo;

import java.util.Arrays;

/**
 * Сотрудник
 */
public class Employee {
    private final String name;
    private Animal[] animals = new Animal[0];

    public Employee(String name) {
        this.name = name;
    }

    /**
     * Добавление животного
     *
     * @param animal Добавляемое животное
     */
    public void addAnimal(Animal animal) {
        animals = Arrays.copyOf(animals, animals.length + 1);
        animals[animals.length - 1] = animal;
    }

    /**
     * Удаление животного
     *
     * @param animal Удаляемое животное
     */
    public void removeAnimal(Animal animal) {
        Animal[] newAnimals = new Animal[animals.length - 1];
        int i = 0;
        for (Animal currentAnimal : animals) {
            if (currentAnimal.equals(animal)) {
                continue;
            }
            newAnimals[i] = currentAnimal;
            i++;
        }
        animals = newAnimals;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
