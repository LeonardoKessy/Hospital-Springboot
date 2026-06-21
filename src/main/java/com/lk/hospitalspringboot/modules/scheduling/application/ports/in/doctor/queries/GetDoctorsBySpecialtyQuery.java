package com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.queries;

import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses.DoctorListResponse;
import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;

public interface GetDoctorsBySpecialtyQuery {
    DoctorListResponse execute(MedicalSpecialty specialty);
}
