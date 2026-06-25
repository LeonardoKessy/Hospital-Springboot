package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;

import java.util.Set;
import java.util.UUID;

public record DoctorProfileResponse(
        UUID id,
        String fullName,
        Set<MedicalSpecialty> specialties,
        String medicalLicense,
        EmployeeStatus status
        ) {

        public static DoctorProfileResponse from(Doctor doctor) {
                return new DoctorProfileResponse(
                        doctor.getId(),
                        doctor.getName().firstName() + " " + doctor.getName().lastName(),
                        doctor.getSpecialties(),
                        doctor.getMedicalLicense(),
                        doctor.getStatus()
                );
        }
}
