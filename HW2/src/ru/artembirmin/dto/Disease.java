package ru.artembirmin.dto;

/**
 * Запись о болезни
 */
public class Disease {
    private final String name;

    public Disease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                '}';
    }
}
