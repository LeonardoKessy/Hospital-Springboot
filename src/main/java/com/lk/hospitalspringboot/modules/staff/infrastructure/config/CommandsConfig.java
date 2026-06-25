package com.lk.hospitalspringboot.modules.staff.infrastructure.config;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.UpdateDoctorPersonalInfo;
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
}
