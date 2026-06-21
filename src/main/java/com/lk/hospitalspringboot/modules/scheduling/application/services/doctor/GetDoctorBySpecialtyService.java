package com.lk.hospitalspringboot.modules.scheduling.application.services.doctor;

import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.queries.GetDoctorsBySpecialtyQuery;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses.DoctorListResponse;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.mappers.DoctorMapper;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.out.DoctorRepositoryPort;
import com.lk.hospitalspringboot.modules.scheduling.domain.models.Doctor;
import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class GetDoctorBySpecialtyService implements GetDoctorsBySpecialtyQuery {
    private final DoctorRepositoryPort doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    @Transactional(readOnly = true)
    public DoctorListResponse execute(MedicalSpecialty specialty) {
        List<Doctor> doctors = doctorRepository.findBySpecialty(specialty);
        return doctorMapper.toDoctorListResponse(doctors);
    }
}
