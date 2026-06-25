package com.lk.hospitalspringboot.modules.shared.domain.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public final class TypeParser {

    private TypeParser() {}

    public static <E extends Enum<E>> E parse(Class<E> enumClass, String value) {
        if (value == null || value.isBlank()) return null;
        return Enum.valueOf(enumClass, value.toUpperCase().trim());
    }

    public static <E extends Enum<E>> Set<E> parse(Class<E> enumClass, Collection<String> values) {
        if (values == null || values.isEmpty()) return Collections.emptySet();
        return values.stream()
                .map(value ->Enum.valueOf(enumClass, value))
                .collect(Collectors.toUnmodifiableSet());
    }


}
