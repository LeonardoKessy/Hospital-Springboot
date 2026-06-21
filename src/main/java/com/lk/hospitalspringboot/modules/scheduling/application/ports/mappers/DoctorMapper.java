package com.lk.hospitalspringboot.modules.scheduling.application.ports.mappers;

import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses.DoctorListResponse;
import com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.responses.DoctorPublicResponse;
import com.lk.hospitalspringboot.modules.scheduling.domain.models.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mapping(target = "firstName", source = "name.firstName")
    @Mapping(target = "lastName", source = "name.lastName")
    DoctorPublicResponse toDoctorPublicResponse(Doctor doctor);

    DoctorListResponse toDoctorListResponse(List<Doctor> doctors);
}
