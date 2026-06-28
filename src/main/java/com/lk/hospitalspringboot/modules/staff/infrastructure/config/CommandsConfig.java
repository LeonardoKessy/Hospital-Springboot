package com.lk.hospitalspringboot.modules.staff.infrastructure.config;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.AddDoctorSpecialty;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RemoveDoctorSpecialty;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.UpdateDoctorSalary;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class CommandsConfig {

    @Bean
    @Transactional
    public RegisterDoctor.Handler registerDoctorHandler(DoctorRepository doctorRepository, UserRepository userRepository) {
        return new RegisterDoctor.Handler(doctorRepository, userRepository);
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
