package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa;

import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffUserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffUserJpaRepository extends JpaRepository<StaffUserJpa, UUID> {
}