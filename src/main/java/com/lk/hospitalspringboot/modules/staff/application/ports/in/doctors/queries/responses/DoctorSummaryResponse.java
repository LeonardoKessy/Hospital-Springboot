package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses;

import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;

import java.util.Set;
import java.util.UUID;

public record DoctorSummaryResponse(
        UUID id,
        String fullName,
        Set<MedicalSpecialty> specialties
) {

    public static DoctorSummaryResponse from(Doctor doctor) {
        return new DoctorSummaryResponse(
                doctor.getEmploymentDetails().getId(),
                doctor.getEmploymentDetails().getName().fullName(),
                doctor.getSpecialties()
        );
    }
}
