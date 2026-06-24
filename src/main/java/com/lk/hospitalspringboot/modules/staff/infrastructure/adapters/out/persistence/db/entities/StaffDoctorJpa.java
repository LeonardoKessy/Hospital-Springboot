package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.enums.ValidCurrencies;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.Money;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "doctors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StaffDoctorJpa {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "identifier_type", nullable = false, length = 50)
    private NationalIdentifier.IdentifierType identifierType;

    @Column(name = "identifier_value", nullable = false, length = 50)
    private String identifierValue;

    @Column(name = "medical_license", nullable = false, length = 100)
    private String medicalLicense;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "contract_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Column(name = "salary_amount", nullable = false)
    private BigDecimal salaryAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_currency", nullable = false)
    private ValidCurrencies salaryCurrency;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "doctor_specialties",
            joinColumns = @JoinColumn(name = "doctor_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "specialty", nullable = false, length = 50)
    private Set<MedicalSpecialty> specialties;

    public Doctor toDomain() {
        return new Doctor(
                this.id,
                new HumanName(this.firstName, this.lastName),
                new NationalIdentifier(this.identifierType, this.identifierValue),
                this.medicalLicense,
                this.specialties,
                this.hireDate,
                this.contractType,
                new Money(this.salaryAmount, this.salaryCurrency),
                this.status
        );
    }

    public static StaffDoctorJpa fromDomain(Doctor dr) {
        return new StaffDoctorJpa(
                dr.getId(),
                dr.getName().firstName(),
                dr.getName().lastName(),
                dr.getIdentifier().identifierType(),
                dr.getIdentifier().identifierValue(),
                dr.getMedicalLicense(),
                dr.getHireDate(),
                dr.getContractType(),
                dr.getSalary().amount(),
                dr.getSalary().currency(),
                dr.getStatus(),
                dr.getSpecialties()
        );
    }

}