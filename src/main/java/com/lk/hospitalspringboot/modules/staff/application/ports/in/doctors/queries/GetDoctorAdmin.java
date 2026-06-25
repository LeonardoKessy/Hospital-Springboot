package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries;

import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorAdminResponse;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class GetDoctorAdmin {

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public DoctorAdminResponse execute(String idStr) {
            UUID id = TypeParser.parseUuid(idStr);

            Doctor doctor = doctorRepository.getById(id);
            return DoctorAdminResponse.from(doctor);
        }
    }
}
