package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.controllers;

import com.lk.hospitalspringboot.modules.shared.application.ports.in.responses.CollectionResponse;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.FetchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctors")
class DoctorController {

    private final RegisterDoctor.Handler registerDoctorHandler;
    private final FetchDoctors.Handler fetchDoctorsHandler;

    @PostMapping
    public ResponseEntity<Void> insert(
            @RequestBody RegisterDoctor.Command command
    ) {
        UUID id = registerDoctorHandler.execute(command);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<CollectionResponse<DoctorSummary>> getDoctors() {
        return ResponseEntity.ok(fetchDoctorsHandler.execute());
    }
}
