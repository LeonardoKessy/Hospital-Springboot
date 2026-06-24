package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa;

import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffDoctorJpaRepository extends JpaRepository<StaffDoctorJpa, UUID> {

    @EntityGraph(attributePaths = {"specialties"})
    @Query("SELECT d FROM StaffDoctorJpa d")
    List<StaffDoctorJpa> findAllWithSpecialties();
}