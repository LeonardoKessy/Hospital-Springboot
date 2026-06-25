package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DoctorAdminResponse(
        UUID id,
        String fullName,
        String identifier,
        String medicalLicense,
        List<MedicalSpecialty> specialties,
        LocalDate hireDate,
        ContractType contractType,
        String salary,
        EmployeeStatus status
) {

    public static DoctorAdminResponse from(Doctor dr) {
        return new DoctorAdminResponse(
                dr.getId(),
                dr.getName().fullName(),
                dr.getIdentifier().identifier(),
                dr.getMedicalLicense(),
                dr.getSpecialties().stream().toList(),
                dr.getHireDate(),
                dr.getContractType(),
                dr.getSalary().money(),
                dr.getStatus()
        );
    }

}
