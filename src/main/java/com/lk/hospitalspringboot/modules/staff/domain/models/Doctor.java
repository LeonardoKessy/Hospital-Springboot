package com.lk.hospitalspringboot.modules.staff.domain.models;

import com.lk.hospitalspringboot.modules.shared.domain.abstracts.AggregateRoot;
import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.BusinessRuleException;
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
    private final UUID id;
    private HumanName name;
    private final NationalIdentifier identifier;
    private final String medicalLicense;
    private final Set<MedicalSpecialty> specialties = new HashSet<>();
    private final LocalDate hireDate;
    private EmployeeStatus status;
    private ContractType contractType;
    private Money salary;


    public Doctor(
            UUID id,
            HumanName name,
            NationalIdentifier identifier,
            String medicalLicense,
            Set<MedicalSpecialty> specialties,
            LocalDate hireDate,
            ContractType contractType,
            Money salary,
            EmployeeStatus status
    ) {
        this.id = Objects.requireNonNull(id, "ID must not be null");
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.identifier = Objects.requireNonNull(identifier, "Identifier must not be null");

        if (medicalLicense == null || medicalLicense.isBlank()) {
            throw new BusinessRuleException(StaffBusinessRules.INVALID_LICENSE);
        }
        this.medicalLicense = medicalLicense;

        if (specialties == null || specialties.isEmpty()) {
            throw new BusinessRuleException(StaffBusinessRules.NO_EMPTY_SPECIALTIES);
        }
        this.specialties.addAll(specialties);

        this.hireDate = Objects.requireNonNull(hireDate, "Hire date must not be null");
        this.contractType = Objects.requireNonNull(contractType, "Contract type must not be null");
        this.salary = Objects.requireNonNull(salary, "Salary must not be null");

        this.status = Objects.requireNonNull(status, "Status must not be null");
    }

    public static Doctor hire(
        UUID id,
        HumanName name,
        NationalIdentifier identifier,
        String medicalLicense,
        Set<MedicalSpecialty> specialties,
        ContractType contractType,
        Money salary
    ) {
        return new Doctor(id, name, identifier,  medicalLicense, specialties, LocalDate.now(), contractType, salary, EmployeeStatus.ACTIVE);
    }

    public Set<MedicalSpecialty> getSpecialties() {
        return Collections.unmodifiableSet(specialties);
    }

    public void addSpecialty(MedicalSpecialty specialty) {
        Objects.requireNonNull(specialty, "Specialty cannot be null");
        specialties.add(specialty);
    }

    public void removeSpecialty(MedicalSpecialty specialty) {
        Objects.requireNonNull(specialty, "Specialty cannot be null");
        if (
                specialties.size() == 1 &&
                specialties.contains(specialty)
        ) {
            throw new BusinessRuleException(StaffBusinessRules.NO_EMPTY_SPECIALTIES);
        }
        specialties.remove(specialty);
    }

    public void setName(HumanName name) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
    }

    public void setContractType(ContractType contractType) {
        this.contractType = Objects.requireNonNull(contractType, "Contract type must not be null");
    }

    public void setSalary(Money salary) {
        this.salary = Objects.requireNonNull(salary, "Salary must not be null");
    }

    public void activate() {
        if (
            this.status == EmployeeStatus.ACTIVE ||
            this.status == EmployeeStatus.TERMINATED
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.ACTIVE;
    }

    public void suspend() {
        if (
                this.status == EmployeeStatus.SUSPENDED ||
                this.status == EmployeeStatus.TERMINATED ||
                this.status == EmployeeStatus.ON_LEAVE
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.SUSPENDED;
    }

    public void absent() {
        if (
            this.status == EmployeeStatus.SUSPENDED ||
            this.status == EmployeeStatus.TERMINATED ||
            this.status == EmployeeStatus.ON_LEAVE
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.ON_LEAVE;
    }

    public void terminate() {
        if (
            this.status == EmployeeStatus.TERMINATED
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.TERMINATED;
    }


}
