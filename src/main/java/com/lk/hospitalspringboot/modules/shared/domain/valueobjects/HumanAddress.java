package com.lk.hospitalspringboot.modules.shared.domain.valueobjects;

import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;

public record HumanAddress(
        String streetAddress,
        String city,
        String state
) {

    public HumanAddress {
        if (streetAddress == null || streetAddress.isBlank())
            throw new InputValidationException("streetAddress", "Street address field cannot be null or blank");
        if (city == null || city.isBlank())
            throw new InputValidationException("city", "City field cannot be null or blank");
        if (state == null || state.isBlank())
            throw new InputValidationException("state", "State field cannot be null or blank");
    }
}
