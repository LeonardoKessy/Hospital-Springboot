package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DoctorAdminResponse(
        UUID id,
        UUID userId,
        String fullName,
        String identifier,
        String enterpriseEmail,
        String medicalLicense,
        List<MedicalSpecialty> specialties,
        LocalDate hireDate,
        LocalDate terminationDate,
        ContractType contractType,
        String salary,
        EmployeeStatus status
) {

    public static DoctorAdminResponse from(Doctor dr) {
        Employee emp = dr.getEmploymentDetails();
        return new DoctorAdminResponse(
                emp.getId(),
                emp.getUserId(),
                emp.getName().fullName(),
                emp.getIdentifier().identifier(),
                emp.getEnterpriseEmail(),
                dr.getMedicalLicense(),
                dr.getSpecialties().stream().toList(),
                emp.getHireDate(),
                emp.getTerminationDate(),
                emp.getContractType(),
                emp.getSalary().money(),
                emp.getStatus()
        );
    }

}
