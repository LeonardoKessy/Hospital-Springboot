package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa.StaffDoctorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public List<Doctor> searchDoctors(SearchDoctors.Query query) {
        Pageable pageable = PageRequest.of(query.page(), query.size(), Sort.by(Sort.Direction.DESC, "id"));

        var doctors = doctorJpaRepository.findWithFilters(
                query.name(),
                query.medicalSpecialty(),
                pageable
        );

        return doctors.stream()
                .map(StaffDoctorJpa::toDomain)
                .toList();
    }

    @Override
    public Doctor getById(UUID id) {
        var doctor = this.doctorJpaRepository.findById(id)
                .map(StaffDoctorJpa::toDomain);

        if (doctor.isPresent()) {
            return doctor.get();
        } else {
            throw new ResourceNotFoundException(ResourceType.DOCTOR, id);
        }
    }

    @Override
    public void save(Doctor doctor) {
        this.doctorJpaRepository.save(StaffDoctorJpa.fromDomain(doctor));
    }
}
