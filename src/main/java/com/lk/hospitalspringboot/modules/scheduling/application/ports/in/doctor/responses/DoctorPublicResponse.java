package com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses;

import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;

import java.util.Set;
import java.util.UUID;

public record DoctorPublicResponse(
        UUID id,
        String firstName,
        String lastName,
        String medicalLicense,
        Set<MedicalSpecialty> specialties
) {
}
