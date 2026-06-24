package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories;

import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa.StaffDoctorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StaffDoctorRepositoryAdapter implements DoctorRepository {
    private final StaffDoctorJpaRepository doctorJpaRepository;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    public void insert(Doctor doctor) {
        doctorJpaRepository.save(StaffDoctorJpa.fromDomain(doctor));
    }
}
