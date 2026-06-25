package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorProfileResponse;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummaryResponse;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.mappers.DoctorMapper;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa.StaffDoctorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<DoctorSummaryResponse> searchDoctors(SearchDoctors.Query query) {
        Pageable pageable = PageRequest.of(query.page(), query.size(), Sort.by(Sort.Direction.DESC, "id"));

        var doctors = doctorJpaRepository.findWithFilters(
                query.name(),
                query.medicalSpecialty(),
                pageable
        );

        return doctors.stream()
                .map(doctorMapper::toDoctorSummaryResponse)
                .toList();
    }

    @Override
    public Optional<DoctorProfileResponse> getById(UUID id) {
        return this.doctorJpaRepository.findById(id)
                .map(doctorMapper::toDoctorProfileResponse);
    }
}
