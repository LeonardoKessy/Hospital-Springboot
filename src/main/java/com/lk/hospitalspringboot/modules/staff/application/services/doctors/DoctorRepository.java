package com.lk.hospitalspringboot.modules.staff.application.services.doctors;

import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;

public interface DoctorRepository {
    void insert(Doctor doctor);
}
