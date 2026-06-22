package com.lk.hospitalspringboot.modules.shared.domain.valueobjects;

public record HumanName(
        String firstName,
        String lastName
) {
    public HumanName {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("firstName and lastName cannot be null");
        }
    }
}
