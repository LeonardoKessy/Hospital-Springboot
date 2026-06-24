package com.lk.hospitalspringboot.modules.staff.infrastructure.config;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandsConfig {

    @Bean
    public RegisterDoctor.Handler registerDoctorHandler(DoctorRepository doctorRepository) {
        return new RegisterDoctor.Handler(doctorRepository);
    }
}
