package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa;

import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffDoctorJpaRepository extends JpaRepository<StaffDoctorJpa, UUID> {

    @Query(
            "SELECT d FROM StaffDoctorJpa d " +
                    "LEFT JOIN FETCH d.employee e " +
                    "LEFT JOIN FETCH e.user u " +
                    "WHERE " +
                    "((:name IS NULL OR " +
                    "u.firstName ILIKE CONCAT('%', :name, '%') OR " +
                    "u.lastName ILIKE CONCAT('%', :name, '%')) AND " +
                    "(:specialty IS NULL OR " +
                    "EXISTS (SELECT s FROM d.specialties s WHERE s = :specialty)))"
    )
    Page<StaffDoctorJpa> findWithFilters(
            @Param("name") String name,
            @Param("specialty") MedicalSpecialty specialty,
            Pageable pageable
    );
}