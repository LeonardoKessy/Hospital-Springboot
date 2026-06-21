package com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.commands;

import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.NationalIdentifier;

import java.util.List;

public record RegisterDoctorCommand(
        String firstName,
        String lastName,
        NationalIdentifier.IdentifierType nationalIdentifierType,
        String nationalIdentifierValue,
        String medicalLicense,
        List<MedicalSpecialty> specialties
) {

    public RegisterDoctorCommand {
        if  (
                firstName == null || firstName.isBlank() ||
                lastName == null  || lastName.isBlank()
        )
            throw new IllegalArgumentException("First and/or last name cannot be null");

        if (nationalIdentifierType == null)
            throw new IllegalArgumentException("National identifier type cannot be null");

        if (nationalIdentifierValue == null || nationalIdentifierValue.isBlank())
            throw new IllegalArgumentException("National identifier value cannot be null");

        if (medicalLicense == null || medicalLicense.isBlank())
            throw new IllegalArgumentException("Medical license cannot be null");

        if (specialties == null || specialties.isEmpty())
            throw new IllegalArgumentException("Specialties cannot be null or empty");

        specialties = List.copyOf(specialties);
    }
}
