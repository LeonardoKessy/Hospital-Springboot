package com.lk.hospitalspringboot.modules.shared.application.ports.in.responses;

import java.util.Collection;

public record CollectionResponse<T>(
        Collection<T> data,
        int page,
        int size,
        int count
) {

    public static <T> CollectionResponse<T> of(
            Collection<T> data,
            int page,
            int size
    ) {
        return new CollectionResponse<>(data, page, size, data.size());
    }
}
