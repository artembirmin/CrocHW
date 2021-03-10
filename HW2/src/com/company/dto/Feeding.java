package com.company.dto;

/**
 * Запись о кормлении
 */
public class Feeding {
    private final String date;

    public Feeding(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Feeding{" +
                "date='" + date + '\'' +
                '}';
    }
}
