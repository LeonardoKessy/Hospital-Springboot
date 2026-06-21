package com.lk.hospitalspringboot.modules.scheduling.application.ports.in.doctor.commands;

import java.util.UUID;

public interface RegisterDoctorUseCase {
     UUID execute(RegisterDoctorCommand command);
}
