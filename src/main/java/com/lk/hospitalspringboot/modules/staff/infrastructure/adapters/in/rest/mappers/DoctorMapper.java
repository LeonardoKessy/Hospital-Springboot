package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.mappers;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummary;
import com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities.StaffDoctorJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(
            target = "fullName",
            expression = "java(jpa.getFirstName() + \" \" + jpa.getLastName())"
    )
    DoctorSummary toDoctorSummaryResponse(StaffDoctorJpa jpa);
}
