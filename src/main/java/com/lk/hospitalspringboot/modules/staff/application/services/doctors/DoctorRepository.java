package com.lk.hospitalspringboot.modules.staff.application.services.doctors;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummary;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;

import java.util.List;

public interface DoctorRepository {
    void insert(Doctor doctor);

    List<DoctorSummary> searchDoctors(SearchDoctors.Query query);
}
