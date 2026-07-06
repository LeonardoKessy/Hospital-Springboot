package com.lk.hospitalspringboot.modules.staff.application.ports.in.employees.commands;

import com.lk.hospitalspringboot.modules.shared.application.ports.out.EventPublisherPort;
import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.EmployeeRepository;
import com.lk.hospitalspringboot.modules.staff.domain.events.EmployeeStatusChangedEvent;
import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class GrantEmployeeVacation {

    public record Command(
       String reason
    ) {
        public Command {
            if (reason == null || reason.isBlank()) {
                throw new InputValidationException("reason", "A reason must be provided");
            }
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final EmployeeRepository employeeRepository;
        private final EventPublisherPort eventPublisher;

        public void execute(String idStr, Command command) {
            UUID id = TypeParser.parseUUID(idStr);

            Employee employee = employeeRepository.findById(id);

            EmployeeStatus oldStatus = employee.getStatus();

            employee.absent();

            eventPublisher.publish(new EmployeeStatusChangedEvent(
                    employee.getId(),
                    oldStatus,
                    employee.getStatus()
            ));

            // TODO: Add incident report.
        }
    }
}
