package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands;

import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class RemoveDoctorSpecialty {

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public void execute(String idStr, String specialtyStr) {
            UUID doctorId = TypeParser.parseUuid(idStr);
            MedicalSpecialty specialty = TypeParser.parseEnum(MedicalSpecialty.class, specialtyStr);

            Doctor doctor = doctorRepository.getById(doctorId);
            doctor.removeSpecialty(specialty);
            doctorRepository.save(doctor);
        }
    }

}
