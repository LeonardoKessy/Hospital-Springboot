package com.lk.hospitalspringboot.modules.scheduling.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.ResourceNotFoundException;

public class AppointmentNotFoundException extends ResourceNotFoundException {
    public AppointmentNotFoundException(Object id) {
      super(ResourceType.APPOINTMENT, id);
    }
}
