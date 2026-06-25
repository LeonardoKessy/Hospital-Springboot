package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.in.rest.controllers;

import com.lk.hospitalspringboot.modules.shared.application.ports.in.responses.CollectionResponse;
import com.lk.hospitalspringboot.modules.shared.infrastructure.config.WebMvcConfig;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.AddDoctorSpecialty;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.RegisterDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.commands.UpdateDoctorPersonalInfo;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.GetDoctor;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.SearchDoctors;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorProfileResponse;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummaryResponse;
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

    private final SearchDoctors.Handler searchDoctorsHandler;
    private final GetDoctor.Handler getDoctorHandler;
    private final UpdateDoctorPersonalInfo.Handler updateDoctorPersonalInfoHandler;
    private final AddDoctorSpecialty.Handler addDoctorSpecialtyHandler;

    @GetMapping
    public ResponseEntity<CollectionResponse<DoctorSummaryResponse>> getDoctors(
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

    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfileResponse> getDoctorById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(getDoctorHandler.execute(id));
    }

    @PutMapping("/{id}/personal-info")
    public ResponseEntity<DoctorProfileResponse> updatePersonalInfo(
            @PathVariable String id,
            @RequestBody UpdateDoctorPersonalInfo.Command command
    ) {
        return ResponseEntity.ok(updateDoctorPersonalInfoHandler.execute(id, command));
    }

    @PostMapping("/{id}/specialties")
    public ResponseEntity<Void> addSpecialty(
            @PathVariable String id,
            @RequestBody AddDoctorSpecialty.Command command
    ) {
        var doctorId = addDoctorSpecialtyHandler.execute(id, command);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(WebMvcConfig.BASE_PATH)
                .path("/doctors/{id}")
                .buildAndExpand(doctorId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
