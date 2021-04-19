package ru.artembirmin.croc.finalhw.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Город.
 */
public class City {
    /**
     * Название города.
     */
    @XmlAttribute
    private final String name;

    /**
     * @param name название города
     */
    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}
