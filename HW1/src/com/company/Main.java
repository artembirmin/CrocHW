package com.company;

public class Main {
    /**
     * Написать метод, форматирующий и выводящий на экран заданный размер в байтах в человекочитаемом виде.
     * Человекочитаемый вид:
     * {целая часть < 1024}.{дробная часть макс. 1 знак} {единица измерения}
     * Например:
     * printBytes(23) -> "23.0 B"
     * printBytes(1024) -> "1.0 KB"
     * printBytes(53692044905543) -> "48.8 TB"
     * Для вывода только одного знака дробной части вещественного числа можно воспользоваться методом String.format:
     * String.format("%.1f", 1.23456)
     */

    public static void main(String[] args) {
        powTestCombination();
        otherTestCombination();
    }

    private static void powTestCombination() {
        expectedValue((long) Math.pow(2, 0), "1b");
        expectedValue((long) Math.pow(2, 10), "1kb");
        expectedValue((long) Math.pow(2, 20), "1mb");
        expectedValue((long) Math.pow(2, 30), "1gb");
        expectedValue((long) Math.pow(2, 40), "1tb");
        expectedValue((long) Math.pow(2, 50), "1pb");
        expectedValue((long) Math.pow(2, 60), "1eb");
    }

    private static void otherTestCombination() {
        expectedValue(23, "23.0b");
        expectedValue(53_692_044_905_543L, "48.8tb");
        expectedValue(1023, "1023.0b");
        expectedValue(1024, "1.0kb");
        expectedValue(1025, "1.0kb");
    }

    private static void expectedValue(long value, String expected) {
        System.out.println("value - " + value + ", " + "expected " + expected);
        printBytes(value);
    }

    private static void printBytes(long bytes) {
        double temp = bytes;
        int degree = 0;
        while (temp > 1023) {
            temp = temp / 1024;
            degree++;
        }
        switch (degree) {
            case 0:
                System.out.println(String.format("%.1f", temp) + " B");
                break;
            case 1:
                System.out.println(String.format("%.1f", temp) + " KB");
                break;
            case 2:
                System.out.println(String.format("%.1f", temp) + " MB");
                break;
            case 3:
                System.out.println(String.format("%.1f", temp) + " GB");
                break;
            case 4:
                System.out.println(String.format("%.1f", temp) + "TB");
                break;
            case 5:
                System.out.println(String.format("%.1f", temp) + "PB");
                break;
            case 6:
                System.out.println(String.format("%.1f", temp) + "EB");
                break;

        }
    }
}
