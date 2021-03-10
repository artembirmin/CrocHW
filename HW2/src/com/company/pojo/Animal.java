package com.company.pojo;

import com.company.dto.Disease;
import com.company.dto.Feeding;

import java.util.Arrays;

/**
 * Животное
 */
public class Animal {
    private final String name;
    private Employee employee;
    private Aviary aviary;
    private Disease[] diseases = new Disease[0];
    private Feeding[] feedings = new Feeding[0];

    public Animal(String name) {
        this.name = name;
    }

    /**
     * Добавление записи о кормлении
     *
     * @param feeding Запись о кормлении
     */
    public void addFeeding(Feeding feeding) {
        feedings = Arrays.copyOf(feedings, feedings.length + 1);
        feedings[feedings.length - 1] = feeding;
    }

    /**
     * Добавление записи о болезни
     *
     * @param disease Запись о болезни
     */
    public void addDisease(Disease disease) {
        diseases = Arrays.copyOf(diseases, diseases.length + 1);
        diseases[diseases.length - 1] = disease;
    }

    public String getName() {
        return name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Aviary getAviary() {
        return aviary;
    }

    public void setAviary(Aviary aviary) {
        this.aviary = aviary;
    }

    public Feeding[] getFeedings() {
        return feedings.clone();
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", employee=" + employee +
                ", aviary=" + aviary +
                ", diseases=" + Arrays.toString(diseases) +
                ", feedings=" + Arrays.toString(feedings) +
                '}';
    }
}
