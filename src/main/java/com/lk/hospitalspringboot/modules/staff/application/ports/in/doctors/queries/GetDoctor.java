package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorProfileResponse;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

public final class GetDoctor {


    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public DoctorProfileResponse execute(String idStr) {
            UUID id = TypeParser.parseUuid(idStr);

            Doctor response = doctorRepository.getById(id);

            return DoctorProfileResponse.from(response);
        }
    }
}
