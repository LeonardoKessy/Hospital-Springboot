package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.UserRepository;
import com.lk.hospitalspringboot.modules.staff.domain.valueobjects.UserRecord;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffUserJpa;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.repositories.jpa.StaffUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StaffUserRepositoryAdapter implements UserRepository {
    private final StaffUserJpaRepository staffUserJpaRepository;

    public UserRecord getById(UUID id) {
        Optional<StaffUserJpa> user = staffUserJpaRepository.findById(id);

        return user.map(StaffUserJpa::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, id));
    }
}
