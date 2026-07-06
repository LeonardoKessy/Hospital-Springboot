package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands;

import com.github.f4b6a3.uuid.UuidCreator;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.enums.ValidCurrencies;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.Money;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.UserRepository;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;
import com.lk.hospitalspringboot.modules.staff.domain.valueobjects.UserRecord;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class RegisterDoctor {

    public record Command (
        String userId,
        String enterpriseEmail,
        String medicalLicense,
        Set<String> specialties,
        String contractType,
        BigDecimal baseSalary,
        String salaryCurrency
    ) {
        public Command {
            InputValidator.initialize()
                    .ensure(() -> userId != null && !userId.isBlank(), "userId", "User ID cannot be null or blank")
                    .ensure(() -> InputValidator.isValidUUID(userId), "userId", "User ID must be a valid UUID")
                    .ensure(() -> enterpriseEmail != null && !enterpriseEmail.isBlank(), "enterpriseEmail", "Enterprise email is required")
                    .ensureEnumCollection(specialties, MedicalSpecialty.class, "specialties", "All given specialties must be valid")
                    .ensureEnum(contractType, ContractType.class, "contractType", "Contract type must be of a valid type")
                    .ensure(
                            () -> baseSalary != null && baseSalary.compareTo(BigDecimal.ZERO) >= 0,
                            "salaryAmount", "Salary amount is required and must be positive"
                            )
                    .ensureEnum(salaryCurrency, ValidCurrencies.class, "salaryCurrency", "Salary currency must be a valid currency")
                    .validate();
        }

        public ValidCurrencies getSalaryCurrency() {
            return TypeParser.parseEnum(ValidCurrencies.class, this.salaryCurrency);
        }

        public ContractType getContractType() {
            return TypeParser.parseEnum(ContractType.class, this.contractType);
        }

        public Set<MedicalSpecialty> getSpecialties() {
            return TypeParser.parseEnum(MedicalSpecialty.class, this.specialties);
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;
        private final UserRepository userRepository;

        public UUID execute(Command command) {
            UUID userId = TypeParser.parseUUID(command.userId);
            UserRecord user = userRepository.getById(userId);

            Money salary = new Money(
                    command.baseSalary(),
                    command.getSalaryCurrency()
            );

            Employee employee = Employee.hire(
                    UuidCreator.getTimeOrderedEpoch(),
                    user.id(),
                    user.name(),
                    user.identifier(),
                    command.enterpriseEmail(),
                    command.getContractType(),
                    salary
            );

            Doctor doctor = Doctor.make(
                    employee,
                    command.medicalLicense(),
                    command.getSpecialties()
            );

            doctorRepository.insert(doctor);

            return doctor.getEmploymentDetails().getId();
        }
    }
}
