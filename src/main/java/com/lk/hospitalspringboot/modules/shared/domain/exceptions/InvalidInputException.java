package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

public class InvalidInputException extends RuntimeException {
    public final String field;

    public InvalidInputException(String field, String message) {
        super(message);
        this.field = field;
    }
}
