package com.lk.hospitalspringboot.modules.staff.domain.events;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.events.DomainEvent;

import java.util.UUID;

public record EmployeeStatusChangedEvent(
        UUID employeeId,
        EmployeeStatus oldStatus,
        EmployeeStatus newStatus
) implements DomainEvent {
}
