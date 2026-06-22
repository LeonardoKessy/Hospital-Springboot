package com.lk.hospitalspringboot.modules.scheduling.application.services.doctor;

import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.commands.RegisterDoctorCommand;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.commands.RegisterDoctorUseCase;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.out.DoctorRepositoryPort;
import com.lk.hospitalspringboot.modules.scheduling.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class RegisterDoctorService implements RegisterDoctorUseCase {
    private final DoctorRepositoryPort doctorRepository;
    private final Supplier<UUID> uuidSupplier;

    @Override
    @Transactional
    public UUID execute(RegisterDoctorCommand command) {
        Doctor doctor = new Doctor(
                uuidSupplier.get(),
                new HumanName(command.firstName(), command.lastName()),
                new NationalIdentifier(command.nationalIdentifierType(), command.nationalIdentifierValue()),
                command.medicalLicense(),
                new HashSet<>(command.specialties())
        );

        doctorRepository.save(doctor);

        return doctor.getId();
    }
}
