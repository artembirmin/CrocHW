package ru.artembirmin.croc.finalhw.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
