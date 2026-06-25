package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorProfileResponse;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

public final class GetDoctor {


    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public DoctorProfileResponse execute(String idStr) {
            if (!InputValidator.isValidUUID(idStr))
                throw new InputValidationException("id", "Received ID is not a valid UUID");

            UUID id = UUID.fromString(idStr);

            Optional<DoctorProfileResponse> response = doctorRepository.getById(id);

            if (response.isEmpty())
                throw new ResourceNotFoundException(ResourceType.DOCTOR, idStr);

            return response.get();
        }
    }
}
