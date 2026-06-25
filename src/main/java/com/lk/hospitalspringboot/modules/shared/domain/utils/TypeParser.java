package com.lk.hospitalspringboot.modules.shared.domain.utils;

import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InvalidTypeConversionException;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class TypeParser {

    private TypeParser() {}

    public static <E extends Enum<E>> E parseEnum(Class<E> enumClass, String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Enum.valueOf(enumClass, value.toUpperCase().trim());
        }  catch (IllegalArgumentException e) {
            throw new InvalidTypeConversionException(value, enumClass.getSimpleName());
        }
    }

    public static <E extends Enum<E>> Set<E> parseEnum(Class<E> enumClass, Collection<String> values) {
        if (values == null || values.isEmpty()) return Collections.emptySet();
        try {
            return values.stream()
                    .map(value -> Enum.valueOf(enumClass, value))
                    .collect(Collectors.toUnmodifiableSet());
        } catch (IllegalArgumentException e) {
            throw new InvalidTypeConversionException("String Collection", enumClass.getSimpleName() + " Collection");
        }
    }

    public static UUID parseUuid(String uuid) {
        if (uuid == null || uuid.isBlank()) return null;
        try  {
            return UUID.fromString(uuid);
        }  catch (IllegalArgumentException e) {
            throw new InvalidTypeConversionException(uuid, "UUID");
        }
    }

}
