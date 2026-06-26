package com.lk.hospitalspringboot.modules.staff.domain.models;

import com.lk.hospitalspringboot.modules.shared.domain.abstracts.AggregateRoot;
import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.BusinessRuleException;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.Money;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.exceptions.StaffBusinessRules;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class Doctor extends AggregateRoot {
    private final String medicalLicense;
    private final Set<MedicalSpecialty> specialties = new HashSet<>();
    private final Employee employmentDetails;


    public Doctor (
            Employee employmentDetails,
            Set<MedicalSpecialty> specialties,
            String medicalLicense
    ) {
        this.employmentDetails = Objects.requireNonNull(employmentDetails, "Employee details must not be null");

        if (medicalLicense == null || medicalLicense.isBlank()) {
            throw new BusinessRuleException(StaffBusinessRules.INVALID_LICENSE);
        }
        this.medicalLicense = medicalLicense;

        if (specialties == null || specialties.isEmpty()) {
            throw new BusinessRuleException(StaffBusinessRules.NO_EMPTY_SPECIALTIES);
        }
        this.specialties.addAll(specialties);

    }

    public static Doctor make(
       Employee employmentDetails,
       String medicalLicense,
       Set<MedicalSpecialty> specialties
    ) {
        return new Doctor(employmentDetails, specialties, medicalLicense);
    }

    public Set<MedicalSpecialty> getSpecialties() {
        return Collections.unmodifiableSet(specialties);
    }

    public void addSpecialty(MedicalSpecialty specialty) {
        Objects.requireNonNull(specialty, "Specialty cannot be null");
        if (specialties.contains(specialty)) {
            throw new BusinessRuleException(StaffBusinessRules.ALREADY_HAS_SPECIALTY);
        }
        specialties.add(specialty);
    }

    public void removeSpecialty(MedicalSpecialty specialty) {
        Objects.requireNonNull(specialty, "Specialty cannot be null");
        if (!specialties.contains(specialty)) {
            throw new BusinessRuleException(StaffBusinessRules.DOES_NOT_HAVE_SPECIALTY);
        }

        if (
                specialties.size() == 1
        ) {
            throw new BusinessRuleException(StaffBusinessRules.NO_EMPTY_SPECIALTIES);
        }
        specialties.remove(specialty);
    }
}
