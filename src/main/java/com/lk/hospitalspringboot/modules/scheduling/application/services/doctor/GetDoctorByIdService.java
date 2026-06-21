package com.lk.hospitalspringboot.modules.scheduling.application.services.doctor;

import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.queries.GetDoctorByIdQuery;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses.DoctorPublicResponse;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.mappers.DoctorMapper;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.out.DoctorRepositoryPort;
import com.lk.hospitalspringboot.modules.scheduling.domain.exceptions.DoctorNotFoundException;
import com.lk.hospitalspringboot.modules.scheduling.domain.models.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class GetDoctorByIdService implements GetDoctorByIdQuery {
    private final DoctorRepositoryPort doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    @Transactional(readOnly = true)
    public DoctorPublicResponse execute(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));

        return doctorMapper.toDoctorPublicResponse(doctor);
    }
}
