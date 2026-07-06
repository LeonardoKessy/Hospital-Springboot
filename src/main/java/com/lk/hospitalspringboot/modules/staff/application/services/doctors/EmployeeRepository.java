package com.lk.hospitalspringboot.modules.staff.application.services.doctors;

import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;

import java.util.UUID;

public interface EmployeeRepository {
    Employee findById(UUID id);

    void save(Employee employee);
}
