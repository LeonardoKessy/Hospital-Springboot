package com.lk.hospitalspringboot.modules.staff.application.services.doctors;

import com.lk.hospitalspringboot.modules.staff.domain.valueobjects.UserRecord;

import java.util.UUID;

public interface UserRepository {
    UserRecord getById(UUID id);
}
