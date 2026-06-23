package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

public class InputValidationException extends RuntimeException {
    public final String field;
    public final String reason;

    public InputValidationException(String field, String reason) {
        super(String.format("Validation failed at field [%s]: %s", field, reason));
        this.field = field;
        this.reason = reason;
    }
}
