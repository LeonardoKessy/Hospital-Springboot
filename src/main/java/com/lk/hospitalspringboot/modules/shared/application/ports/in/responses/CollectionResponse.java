package com.lk.hospitalspringboot.modules.shared.application.ports.in.responses;

import java.util.Collection;

public record CollectionResponse<T>(
        Collection<T> data,
        int count
) {

    public static <T> CollectionResponse<T> of(
            Collection<T> data
    ) {
        return new CollectionResponse<>(data, data.size());
    }
}
