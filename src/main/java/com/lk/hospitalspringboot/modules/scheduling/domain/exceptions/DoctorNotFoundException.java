package com.lk.hospitalspringboot.modules.scheduling.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;

public class DoctorNotFoundException extends ResourceNotFoundException {
    public DoctorNotFoundException(Object id) {
        super(ResourceType.DOCTOR, id);
    }
}
