package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

public class InvalidInputException extends RuntimeException {
    public final String field;
    public final String reason;

    public InvalidInputException(String field, String reason) {
        super("An invalid input was received");
        this.field = field;
        this.reason = reason;
    }
}
