package com.company.pojo;

import com.company.dto.Cleaning;
import com.company.dto.Disease;
import com.company.dto.Feeding;

import java.util.Arrays;

/**
 * Зоопарк
 */
public class Zoo {

    private final Employee[] employees;
    private final Aviary[] aviaries;
    private Animal[] animals = new Animal[0];

    public Zoo(Employee[] employees, Aviary[] aviaries) {
        this.employees = employees;
        this.aviaries = aviaries;
    }

    /**
     * Добавление животного
     *
     * @param animal   Добавляемое животное
     * @param employee Прикрепляемый к нему работник
     * @param aviary   Вольер животного
     */
    public void addAnimal(Animal animal,
                          Employee employee,
                          Aviary aviary) {
        animal.setEmployee(employee);
        employee.addAnimal(animal);
        animal.setAviary(aviary);
        aviary.addAnimal(animal);
        animals = Arrays.copyOf(animals, animals.length + 1);
        animals[animals.length - 1] = animal;
    }

    /**
     * Добавление записи о болезни животного
     *
     * @param animal  Заболевшее животное
     * @param disease Болезнь
     */
    public void addDisease(Animal animal, Disease disease) {
        animal.addDisease(disease);
    }


    /**
     * Добавление записи об уборке в вольере
     *
     * @param aviary   Вольер
     * @param cleaning Запись об уборке
     */
    public void addCleaning(Aviary aviary, Cleaning cleaning) {
        aviary.addCleaning(cleaning);
    }


    /**
     * Добавление записи о кормлении животного
     *
     * @param animal  Животное, которое кормили
     * @param feeding Запись о кормлении
     */
    public void addFeeding(Animal animal, Feeding feeding) {
        animal.addFeeding(feeding);
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
                animal.getAviary().removeAnimal(animal);
                animal.getEmployee().removeAnimal(animal);
                continue;
            }
            newAnimals[i] = currentAnimal;
            i++;
        }
        animals = newAnimals;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "employees=" + Arrays.toString(employees) +
                ",\naviaries=" + Arrays.toString(aviaries) +
                ",\nanimals=" + Arrays.toString(animals) +
                "}\n";
    }
}
