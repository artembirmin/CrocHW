package com.company.pojo;

import com.company.dto.Cleaning;

import java.util.Arrays;

/**
 * Вольер
 */
public class Aviary {
    private final String number;
    private Animal[] animals = new Animal[0];
    private Cleaning[] cleanings = new Cleaning[0];

    public Aviary(String number) {
        this.number = number;
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
     * Добавление записи об уборке
     *
     * @param cleaning Добавляемая запись
     */
    public void addCleaning(Cleaning cleaning) {
        cleanings = Arrays.copyOf(cleanings, cleanings.length + 1);
        cleanings[cleanings.length - 1] = cleaning;
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

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Aviary{" +
                "number='" + number + '\'' +
                ", cleanings=" + Arrays.toString(cleanings) +
                '}';
    }
}
