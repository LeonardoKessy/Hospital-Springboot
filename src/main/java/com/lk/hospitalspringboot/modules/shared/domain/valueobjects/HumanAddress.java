package com.lk.hospitalspringboot.modules.shared.domain.valueobjects;

public record HumanAddress(
        String streetAddress,
        String city,
        String state
) {

    public HumanAddress {
        if (streetAddress == null || streetAddress.isEmpty())
            throw new IllegalArgumentException("streetAddress cannot be null or empty");
        if (city == null || city.isEmpty())
            throw new IllegalArgumentException("city cannot be null or empty");
        if (state == null || state.isEmpty())
            throw new IllegalArgumentException("state cannot be null or empty");
    }
}
