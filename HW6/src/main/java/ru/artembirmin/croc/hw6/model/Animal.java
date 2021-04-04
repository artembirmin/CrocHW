package ru.artembirmin.croc.hw6.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal {
    @XmlElement
    private String name;
    @XmlElementWrapper
    @XmlElement
    private List<String> appearance = new ArrayList<>();

    public Animal(String name, List<String> appearance) {
        this.name = name;
        this.appearance = appearance;
    }

    public Animal() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) && Objects.equals(appearance, animal.appearance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, appearance);
    }
}