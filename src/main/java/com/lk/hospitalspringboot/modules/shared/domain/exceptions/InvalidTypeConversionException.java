package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import lombok.Getter;

@Getter
public class InvalidTypeConversionException extends RuntimeException {
    private final String code = "INVALID_TYPE_CONVERSION";

    public InvalidTypeConversionException(String value, String type) {
        super(String.format("Could not convert %s to %s.", value, type));
    }
}
