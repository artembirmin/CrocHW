package ru.artembirmin.dto;

/**
 * Запись об уборке
 */
public class Cleaning {
    private final String date;

    public Cleaning(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Cleaning{" +
                "date='" + date + '\'' +
                '}';
    }
}
