package ru.artembirmin.croc.finalhw.model;

/**
 * Город.
 */
public class City {
    /**
     * Название города.
     */
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
