package com.lk.hospitalspringboot.modules.scheduling.domain.models;

import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.NationalIdentifier;
import lombok.Getter;

import java.util.*;

@Getter
public class Doctor {
    private final UUID id;
    private HumanName name;
    private NationalIdentifier nationalIdentifier;
    private final String medicalLicense;
    private final Set<MedicalSpecialty> specialties = new HashSet<>();
    private boolean active;

    public Doctor(
            UUID id,
            HumanName name,
            NationalIdentifier nationalIdentifier,
            String medicalLicense,
            Set<MedicalSpecialty> specialties
    ) {
        this.id = Objects.requireNonNull(id, "Id must not be null");
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.nationalIdentifier = Objects.requireNonNull(nationalIdentifier, "National Identifier must not be null");

        if (medicalLicense == null || medicalLicense.isBlank()) {
            throw new IllegalArgumentException("Medical License cannot be null or blank");
        }
        this.medicalLicense = medicalLicense;

        if (specialties == null || specialties.isEmpty())
            throw new IllegalArgumentException("Specialties cannot be null or empty");
        this.specialties.addAll(specialties);

        this.active = true;
    }

    public void setName(HumanName name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public Set<MedicalSpecialty> getSpecialties() {
        return Collections.unmodifiableSet(specialties);
    }

    public void setNationalIdentifier(NationalIdentifier nationalIdentifier) {
        if (nationalIdentifier == null) {
            throw new IllegalArgumentException("National Identifier cannot be null");
        }
        this.nationalIdentifier = nationalIdentifier;
    }

    public void addSpecialty(MedicalSpecialty specialty) {
        this.specialties.add(specialty);
    }

    public void removeSpecialty(MedicalSpecialty specialty) {
        this.specialties.remove(specialty);
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
