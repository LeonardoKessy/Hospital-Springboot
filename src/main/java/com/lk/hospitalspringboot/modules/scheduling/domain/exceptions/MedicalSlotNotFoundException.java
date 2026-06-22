package com.lk.hospitalspringboot.modules.scheduling.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;

public class MedicalSlotNotFoundException extends ResourceNotFoundException {
    public MedicalSlotNotFoundException(Object id) {
        super(ResourceType.MEDICAL_SLOT, id);
    }
}
