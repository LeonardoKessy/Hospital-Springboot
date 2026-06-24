package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummary;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.mappers.DoctorMapper;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa.StaffDoctorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StaffDoctorRepositoryAdapter implements DoctorRepository {
    private final StaffDoctorJpaRepository doctorJpaRepository;
    private final DoctorMapper doctorMapper;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    public void insert(Doctor doctor) {
        doctorJpaRepository.save(StaffDoctorJpa.fromDomain(doctor));
    }

    @Override
    public List<DoctorSummary> fetchAllDoctors() {
        var doctors = doctorJpaRepository.findAllWithSpecialties();

        return doctors.stream()
                .map(doctorMapper::toDoctorSummaryResponse)
                .toList();
    }
}
