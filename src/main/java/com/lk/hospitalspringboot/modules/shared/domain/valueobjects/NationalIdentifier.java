package com.lk.hospitalspringboot.modules.shared.domain.valueobjects;

public record NationalIdentifier(
        IdentifierType identifierType,
        String identifierValue
) {
    public enum IdentifierType {
        DNI, SSN, PASSPORT
    }

    public NationalIdentifier {
        if (identifierValue  == null) {
            throw new IllegalArgumentException("identifierValue cannot be null");
        }

        if (identifierType == null) {
            throw new IllegalArgumentException("identifierType cannot be null");
        }
    }
}
