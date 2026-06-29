package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.controllers;

import com.lk.hospitalspringboot.modules.shared.infrastructure.config.WebMvcConfig;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.UpdateDoctorSalary;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.GetDoctorAdmin;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorAdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
class StaffController {

    private final RegisterDoctor.Handler registerDoctorHandler;
    private final GetDoctorAdmin.Handler getDoctorAdminHandler;
    private final UpdateDoctorSalary.Handler updateDoctorSalaryHandler;

    @PostMapping("/doctors")
    public ResponseEntity<Void> insert(
            @RequestBody RegisterDoctor.Command command
    ) {
        UUID id = registerDoctorHandler.execute(command);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(WebMvcConfig.BASE_PATH)
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorAdminResponse>  getDoctor(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(getDoctorAdminHandler.execute(id));
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<DoctorAdminResponse> updateSalary(
            @PathVariable String id,
            @RequestBody UpdateDoctorSalary.Command command
    ) {
        return ResponseEntity.ok(updateDoctorSalaryHandler.execute(id, command));
    }
}
