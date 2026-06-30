package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.EmployeeRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffEmployeeJpa;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa.StaffEmployeeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StaffEmployeeRepositoryAdapter implements EmployeeRepository {
    private final StaffEmployeeJpaRepository staffEmployeeJpaRepository;

    @Override
    public Employee findById(UUID id) {
        return staffEmployeeJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceType.EMPLOYEE, id))
                .toDomain();
    }


    @Override
    public void save(Employee employee) {
        staffEmployeeJpaRepository.save(StaffEmployeeJpa.from(employee));
    }
}
