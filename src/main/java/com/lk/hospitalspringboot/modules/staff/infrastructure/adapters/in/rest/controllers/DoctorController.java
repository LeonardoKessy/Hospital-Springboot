package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.controllers;

import com.lk.hospitalspringboot.modules.shared.application.ports.in.responses.CollectionResponse;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctors")
class DoctorController {

    private final RegisterDoctor.Handler registerDoctorHandler;
    private final SearchDoctors.Handler searchDoctorsHandler;

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
    public ResponseEntity<CollectionResponse<DoctorSummary>> getDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialty,
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable
    ) {
        var query = new SearchDoctors.Query(
                name,
                specialty,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        return ResponseEntity.ok(searchDoctorsHandler.execute(query));
    }
}
