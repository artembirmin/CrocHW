package ru.artembirmin.croc.hw5.model.exceptions;

import java.io.IOException;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
