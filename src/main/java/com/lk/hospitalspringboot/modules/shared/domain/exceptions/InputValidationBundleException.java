package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

public class InputValidationBundleException extends RuntimeException {
    private final Map<String, String> errors;

    @Getter
    private final String code = "INVALID_INPUTS_BUNDLE";

    public InputValidationBundleException(Map<String, String> errors) {
        super("Input validation failed with one or more errors");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return Collections.unmodifiableMap(errors);
    }

}
