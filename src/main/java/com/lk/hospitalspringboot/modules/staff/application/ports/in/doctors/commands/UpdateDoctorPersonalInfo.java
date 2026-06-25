package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorProfileResponse;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class UpdateDoctorPersonalInfo {

    public record Command(
            String firstName,
            String lastName
    ) {
        public Command {
            InputValidator.initialize()
                    .ensure(() -> firstName != null && !firstName.isBlank(), "firstName", "First name must not be empty")
                    .ensure(() -> lastName != null && !lastName.isBlank(), "lastName", "Last name must not be empty")
                    .validate();
        }
    }


    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public DoctorProfileResponse execute(String idStr, Command command) {
            UUID id = TypeParser.parseUuid(idStr);

            var doctor = doctorRepository.getById(id);
            HumanName name = new HumanName(command.firstName, command.lastName);
            doctor.setName(name);

            doctorRepository.save(doctor);
            return DoctorProfileResponse.from(doctor);
        }
    }
}
