package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands;

import com.github.f4b6a3.uuid.UuidCreator;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.enums.ValidCurrencies;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.Money;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class RegisterDoctor {

    public record Command (
        String firstName,
        String lastName,
        String identifierValue,
        String identifierType,
        String medicalLicense,
        Set<String> specialties,
        String contractType,
        BigDecimal salaryAmount,
        String salaryCurrency
    ) {
        public Command {
            InputValidator.initialize()
                    .ensure(() -> firstName != null && !firstName.isBlank(), "firstName", "First name is required")
                    .ensure(() -> lastName != null && !lastName.isBlank(), "lastName", "Last name is required")
                    .ensure(() -> identifierValue != null && !identifierValue.isBlank(), "identifierValue", "Identifier value is required")
                    .ensureEnum(identifierType, NationalIdentifier.IdentifierType.class, "identifierType", "Identifier type must be of a valid type")
                    .ensureEnumCollection(specialties, MedicalSpecialty.class, "specialties", "All given specialties must be valid")
                    .ensureEnum(contractType, ContractType.class, "contractType", "Contract type must be of a valid type")
                    .ensure(
                            () -> salaryAmount != null && salaryAmount.compareTo(BigDecimal.ZERO) >= 0,
                            "salaryAmount", "Salary amount is required and must be positive"
                            )
                    .ensureEnum(salaryCurrency, ValidCurrencies.class, "salaryCurrency", "Salary currency must be a valid currency")
                    .validate();
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public UUID execute(Command command) {
            HumanName name = new HumanName(command.firstName(), command.lastName());

            NationalIdentifier identifier = new NationalIdentifier(
                    TypeParser.parseEnum(
                            NationalIdentifier.IdentifierType.class,
                            command.identifierType()
                            ),
                    command.identifierValue()
            );

            Set<MedicalSpecialty> specialties = TypeParser.parseEnum(
                    MedicalSpecialty.class,
                    command.specialties()
            );

            ContractType contractType = TypeParser.parseEnum(
                    ContractType.class,
                    command.contractType()
            );

            Money salary = new Money(
                    command.salaryAmount(),
                    TypeParser.parseEnum(
                            ValidCurrencies.class,
                            command.salaryCurrency()
                    )
            );

            Doctor doctor = Doctor.hire(
                    UuidCreator.getTimeOrderedEpoch(),
                    name,
                    identifier,
                    command.medicalLicense(),
                    specialties,
                    contractType,
                    salary
            );

            doctorRepository.insert(doctor);

            return doctor.getId();
        }
    }
}
