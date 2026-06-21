package com.lk.hospitalspringboot.modules.scheduling.application.ports.out;

import com.lk.hospitalspringboot.modules.scheduling.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepositoryPort {
    void save(Doctor doctor);
    Optional<Doctor> findById(UUID id);
    List<Doctor> findBySpecialty(MedicalSpecialty specialty);
}
