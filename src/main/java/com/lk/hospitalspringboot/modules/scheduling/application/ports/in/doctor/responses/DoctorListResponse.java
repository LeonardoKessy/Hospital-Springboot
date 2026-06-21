package com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses;

import java.util.List;

public record DoctorListResponse(
        List<DoctorPublicResponse> doctors
) {
}
