package ru.artembirmin.croc.hw6.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "zoo")
public class Zoo {
    @XmlElement
    private String title;
    @XmlElement(name = "Управляющий")
    private String manager;
        @XmlElementWrapper(name = "Животные")
        @XmlElement(name = "Животное")
    private List<Animal> animals = new ArrayList<>();


    public Zoo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zoo zoo = (Zoo) o;
        return Objects.equals(title, zoo.title) && Objects.equals(manager, zoo.manager) && Objects.equals(animals, zoo.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, manager, animals);
    }
}