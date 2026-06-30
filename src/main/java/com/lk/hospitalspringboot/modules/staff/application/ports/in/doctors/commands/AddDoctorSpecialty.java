package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands;

import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class AddDoctorSpecialty {

    public record Command (
        String specialty
    ) {
        public Command {
            InputValidator.isValidEnum(MedicalSpecialty.class, specialty);
        }

        public MedicalSpecialty medicalSpecialty() {
            return TypeParser.parseEnum(MedicalSpecialty.class, specialty);
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public UUID execute(String idStr, Command command) {
            UUID id = TypeParser.parseUUID(idStr);
            MedicalSpecialty specialty = command.medicalSpecialty();

            Doctor doctor = doctorRepository.getById(id);
            doctor.addSpecialty(specialty);
            doctorRepository.save(doctor);

            return doctor.getEmploymentDetails().getId();
        }
    }
}
