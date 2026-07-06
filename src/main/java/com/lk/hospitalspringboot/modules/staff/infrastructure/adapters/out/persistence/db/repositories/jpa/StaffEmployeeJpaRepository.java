package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa;

import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffEmployeeJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffEmployeeJpaRepository extends JpaRepository<StaffEmployeeJpa, UUID> {

}