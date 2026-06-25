package com.lk.hospitalspringboot.modules.staff.application.services.doctors;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorProfileResponse;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummaryResponse;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository {
    void insert(Doctor doctor);

    List<DoctorSummaryResponse> searchDoctors(SearchDoctors.Query query);

    Optional<DoctorProfileResponse> getById(UUID id);
}
