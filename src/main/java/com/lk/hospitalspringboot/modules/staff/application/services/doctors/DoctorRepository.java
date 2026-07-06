package com.lk.hospitalspringboot.modules.staff.application.services.doctors;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository {
    void insert(Doctor doctor);

    List<Doctor> searchDoctors(SearchDoctors.Query query);

    Doctor getById(UUID id);

    void save(Doctor doctor);
}
