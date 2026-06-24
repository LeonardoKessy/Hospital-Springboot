package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import java.util.Map;

public class InputValidationBundleException extends RuntimeException {
    private final Map<String, String> errors;

    public InputValidationBundleException(Map<String, String> errors) {
        super("Input validation failed with one or more errors");
        this.errors = errors;
    }
}
