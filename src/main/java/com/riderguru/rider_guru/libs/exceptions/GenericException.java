package com.riderguru.rider_guru.libs.exceptions;

public class GenericException extends RuntimeException {
    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, Throwable error) {
        super(message, error);
    }
}
