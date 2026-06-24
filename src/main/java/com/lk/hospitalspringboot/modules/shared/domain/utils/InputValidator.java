package com.lk.hospitalspringboot.modules.shared.domain.utils;

import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationBundleException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class InputValidator {
    private final Map<String, String> errors = new HashMap<>();

    private InputValidator() {}

    public static InputValidator initialize() {
        return new InputValidator();
    }

    public InputValidator ensure(
            BooleanSupplier condition,
            String fieldName,
            String reason
    ) {
        try {
            if (condition.getAsBoolean()) {
                this.errors.put(fieldName, reason);
            }
        } catch (Exception e) {
            this.errors.put(fieldName, "System failed to evaluate field structure");
        }
        return this;
    }

    public InputValidator ensureEnum(String value, Class<? extends Enum<?>> enumClass, String field, String reason) {
        if (value == null || value.isBlank()) {
            this.errors.put(field, reason);
            return this;
        }

        boolean matches = Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(value));

        if (!matches) {
            this.errors.put(field, reason);
        }
        return this;
    }

    public void validate() {
        if (!this.errors.isEmpty()) {
            throw new InputValidationBundleException(this.errors);
        }
    }
}
