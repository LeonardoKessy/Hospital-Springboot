package com.lk.hospitalspringboot.modules.staff.domain.valueobjects;

import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;

import java.util.UUID;

public record UserRecord(
        UUID id,
        HumanName name,
        NationalIdentifier identifier
) {
}
