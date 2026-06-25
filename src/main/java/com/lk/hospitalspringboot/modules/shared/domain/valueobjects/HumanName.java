package com.lk.hospitalspringboot.modules.shared.domain.valueobjects;

import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;

public record HumanName(
        String firstName,
        String lastName
) {
    public HumanName {
        if (firstName == null) {
            throw new InputValidationException("firstName", "First name field cannot be null");
        }
        if (lastName == null) {
            throw new InputValidationException("lastName", "Last name field cannot be null");
        }
    }

    public String fullName() {
        return firstName + " " + lastName;
    }
}
