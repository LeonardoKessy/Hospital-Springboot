package com.lk.hospitalspringboot.modules.staff.domain.valueobjects;

import com.lk.hospitalspringboot.modules.staff.domain.enums.IncidentType;

import java.util.UUID;

public record EmployeeIncident(
        UUID id,
        UUID employeeId,
        IncidentType type,
        String reason
) {}