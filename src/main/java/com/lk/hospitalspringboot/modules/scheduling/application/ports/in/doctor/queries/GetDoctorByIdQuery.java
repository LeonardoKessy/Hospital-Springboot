package com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.queries;

import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses.DoctorPublicResponse;

import java.util.UUID;

public interface GetDoctorByIdQuery {
    DoctorPublicResponse execute(UUID id);
}
