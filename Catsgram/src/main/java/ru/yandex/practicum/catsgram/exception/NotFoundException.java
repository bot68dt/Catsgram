package ru.yandex.practicum.catsgram.exception;

import java.io.IOException;

public class NotFoundException extends IOException {
    public NotFoundException(final String message) {
        super(message);
    }
}
