package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.mappers;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorProfileResponse;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummaryResponse;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.print.Doc;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(
            target = "fullName",
            expression = "java(jpa.getFirstName() + \" \" + jpa.getLastName())"
    )
    DoctorSummaryResponse toDoctorSummaryResponse(StaffDoctorJpa jpa);

    @Mapping(
            target = "fullName",
            expression = "java(jpa.getFirstName() + \" \" + jpa.getLastName())"
    )
    DoctorProfileResponse toDoctorProfileResponse(StaffDoctorJpa jpa);
}
