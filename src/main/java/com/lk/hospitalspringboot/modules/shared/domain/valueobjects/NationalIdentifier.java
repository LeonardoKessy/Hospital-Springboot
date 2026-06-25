package com.lk.hospitalspringboot.modules.shared.domain.valueobjects;

import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;

public record NationalIdentifier(
        IdentifierType identifierType,
        String identifierValue
) {
    public enum IdentifierType {
        DNI, SSN, PASSPORT
    }

    public NationalIdentifier {
        if (identifierValue  == null) {
            throw new InputValidationException("identifierValue", "Identifier amount cannot be null");
        }

        if (identifierType == null) {
            throw new InputValidationException("identifierType", "Identifier type cannot be null");
        }
    }

    public String identifier() {
        return identifierType.name() + " " + identifierValue;
    }
}
