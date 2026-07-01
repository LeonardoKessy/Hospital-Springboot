package com.lk.hospitalspringboot.modules.staff.infrastructure.config;

import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.AddDoctorSpecialty;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RemoveDoctorSpecialty;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.employees.commands.*;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.EmployeeRepository;
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
    public UpdateEmployeeSalary.Handler updateDoctorSalaryHandler(EmployeeRepository employeeRepository) {
        return new UpdateEmployeeSalary.Handler(employeeRepository);
    }

    @Bean
    @Transactional
    public UpdateEmployeeContract.Handler  updateEmployeeContractHandler(EmployeeRepository employeeRepository) {
        return new UpdateEmployeeContract.Handler(employeeRepository);
    }

    @Bean
    @Transactional
    public SuspendEmployee.Handler suspendEmployeeHandler(EmployeeRepository employeeRepository) {
        return new SuspendEmployee.Handler(employeeRepository);
    }

    @Bean
    @Transactional
    public TerminateEmployee.Handler terminateEmployeeHandler(EmployeeRepository employeeRepository) {
        return new TerminateEmployee.Handler(employeeRepository);
    }

    @Bean
    @Transactional
    public GrantEmployeeVacation.Handler grantEmployeeVacationHandler(EmployeeRepository employeeRepository) {
        return new GrantEmployeeVacation.Handler(employeeRepository);
    }
}
