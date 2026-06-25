package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ValidCurrencies;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.Money;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorAdminResponse;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.domain.models.Doctor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateDoctorSalary {

    public record Command (
            BigDecimal amount,
            String currency
    ) {
        public Command {
            InputValidator.initialize()
                    .ensure(() -> amount != null, "amount", "Salary amount cannot be null")
                    .ensure(() -> currency != null, "currency", "Salary currency cannot be null")
                    .ensureEnum(currency, ValidCurrencies.class, "currency", "Salary currency must be a valid currency")
                    .validate();
        }

        public Money money() {
            return new Money(amount, TypeParser.parseEnum(ValidCurrencies.class, currency));
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public DoctorAdminResponse execute(String idStr, Command command) {
            UUID id = TypeParser.parseUuid(idStr);
            Money money = command.money();

            Doctor doctor = doctorRepository.getById(id);
            doctor.setSalary(money);
            doctorRepository.save(doctor);

            return DoctorAdminResponse.from(doctor);
        }
    }
}
