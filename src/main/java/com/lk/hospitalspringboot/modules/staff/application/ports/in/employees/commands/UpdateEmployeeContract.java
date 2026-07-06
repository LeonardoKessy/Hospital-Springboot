package com.lk.hospitalspringboot.modules.staff.application.ports.in.employees.commands;

import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.EmployeeRepository;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class UpdateEmployeeContract {

    public record Command(
            String contractType
    ) {
        public Command {
            if (contractType == null || contractType.isBlank()) {
                throw  new InputValidationException("contractType", "Contract type must not be null or blank");
            } else if (InputValidator.isValidEnum(ContractType.class, contractType)) {
                throw  new InputValidationException("contractType", "Contract type must be a valid type of contract");
            }
        }

        public ContractType getContractType() {
            return TypeParser.parseEnum(ContractType.class, contractType);
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final EmployeeRepository employeeRepository;

        public void execute(String idStr, Command command) {
            UUID id = TypeParser.parseUUID(idStr);

            Employee employee = employeeRepository.findById(id);
            employee.setContractType(command.getContractType());

            employeeRepository.save(employee);
        }
    }
}
