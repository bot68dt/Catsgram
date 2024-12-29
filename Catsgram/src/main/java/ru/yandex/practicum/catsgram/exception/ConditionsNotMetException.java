package ru.yandex.practicum.catsgram.exception;

import java.io.IOException;

public class ConditionsNotMetException extends IOException {
    public ConditionsNotMetException(final String message) {
        super(message);
    }
}