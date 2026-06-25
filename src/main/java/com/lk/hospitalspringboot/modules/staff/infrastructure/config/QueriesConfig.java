package com.lk.hospitalspringboot.modules.staff.infrastructure.config;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.GetDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class QueriesConfig {

    @Bean
    @Transactional(readOnly = true)
    public SearchDoctors.Handler fetchDoctorsHandler(DoctorRepository doctorRepository) {
        return new SearchDoctors.Handler(doctorRepository);
    }

    @Bean
    @Transactional(readOnly = true)
    public GetDoctor.Handler fetchDoctorHandler(DoctorRepository doctorRepository) {
        return new GetDoctor.Handler(doctorRepository);
    }
}
