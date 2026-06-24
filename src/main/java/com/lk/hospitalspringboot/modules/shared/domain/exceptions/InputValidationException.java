package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import lombok.Getter;

@Getter
public class InputValidationException extends RuntimeException {
    private final String code = "INVALID_INPUT";

    public InputValidationException(String field, String reason) {
        super(String.format("Validation failed at field [%s]: %s", field, reason));
    }
}
