package com.itcompany.itcompany.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String resourceName, Long id) {
        super(String.format("%s не найден с ID: %d", resourceName, id));
    }
}
