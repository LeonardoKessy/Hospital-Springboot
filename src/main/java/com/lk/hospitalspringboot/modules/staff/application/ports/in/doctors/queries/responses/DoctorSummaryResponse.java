package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses;

import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;

import java.util.Set;
import java.util.UUID;

public record DoctorSummaryResponse(
        UUID id,
        String fullName,
        Set<MedicalSpecialty> specialties
) {
}
