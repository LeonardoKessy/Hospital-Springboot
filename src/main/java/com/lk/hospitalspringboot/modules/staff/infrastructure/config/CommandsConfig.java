package com.lk.hospitalspringboot.modules.staff.infrastructure.config;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.*;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class CommandsConfig {

    @Bean
    @Transactional
    public RegisterDoctor.Handler registerDoctorHandler(DoctorRepository doctorRepository) {
        return new RegisterDoctor.Handler(doctorRepository);
    }

    @Bean
    @Transactional
    public UpdateDoctorPersonalInfo.Handler updateDoctorPersonalInfoHandler(DoctorRepository doctorRepository) {
        return new UpdateDoctorPersonalInfo.Handler(doctorRepository);
    }

    @Bean
    @Transactional
    public AddDoctorSpecialty.Handler addDoctorSpecialtyHandler(DoctorRepository doctorRepository) {
        return new AddDoctorSpecialty.Handler(doctorRepository);
    }

    @Bean
    @Transactional
    public RemoveDoctorSpecialty.Handler removeDoctorSpecialtyHandler(DoctorRepository doctorRepository) {
        return new RemoveDoctorSpecialty.Handler(doctorRepository);
    }

    @Bean
    @Transactional
    public UpdateDoctorSalary.Handler updateDoctorSalaryHandler(DoctorRepository doctorRepository) {
        return new UpdateDoctorSalary.Handler(doctorRepository);
    }
}
