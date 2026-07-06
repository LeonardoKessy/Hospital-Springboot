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
import org.hibernate.annotations.BatchSize;

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

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", nullable = false)
    private StaffEmployeeJpa employee;

    @Column(name = "medical_license", nullable = false, length = 100)
    private String medicalLicense;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "doctor_specialties",
            joinColumns = @JoinColumn(name = "doctor_id")
    )
    @Enumerated(EnumType.STRING)
    @BatchSize(size = 10)
    @Column(name = "specialty", nullable = false, length = 50)
    private Set<MedicalSpecialty> specialties;

    public Doctor toDomain() {
        return new Doctor(
                this.employee.toDomain(),
                this.specialties,
                this.medicalLicense
        );
    }

    public static StaffDoctorJpa fromDomain(Doctor dr) {
        return new StaffDoctorJpa(
                null,
                StaffEmployeeJpa.from(dr.getEmploymentDetails()),
                dr.getMedicalLicense(),
                dr.getSpecialties()
        );
    }

}