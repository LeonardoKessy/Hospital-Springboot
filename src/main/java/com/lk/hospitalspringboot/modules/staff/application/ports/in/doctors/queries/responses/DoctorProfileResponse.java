package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;

import java.util.Set;
import java.util.UUID;

public record DoctorProfileResponse(
        UUID id,
        String fullName,
        String enterpriseEmail,
        Set<MedicalSpecialty> specialties,
        String medicalLicense,
        EmployeeStatus status
        ) {

        public static DoctorProfileResponse from(Doctor doctor) {
                Employee emp = doctor.getEmploymentDetails();
                return new DoctorProfileResponse(
                        emp.getId(),
                        emp.getName().fullName(),
                        emp.getEnterpriseEmail(),
                        doctor.getSpecialties(),
                        doctor.getMedicalLicense(),
                        emp.getStatus()
                );
        }
}
