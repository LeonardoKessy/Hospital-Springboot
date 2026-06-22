package com.lk.hospitalspringboot.modules.scheduling.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;

public class PatientNotFoundException extends ResourceNotFoundException {
    public PatientNotFoundException(Object id) {
        super(ResourceType.PATIENT, id);
    }
}
