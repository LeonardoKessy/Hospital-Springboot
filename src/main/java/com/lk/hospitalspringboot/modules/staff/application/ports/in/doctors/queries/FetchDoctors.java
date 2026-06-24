package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries;

import com.lk.hospitalspringboot.modules.shared.application.ports.in.responses.CollectionResponse;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummary;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import lombok.RequiredArgsConstructor;

public final class FetchDoctors {

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public CollectionResponse<DoctorSummary> execute() {
            var doctors = doctorRepository.fetchAllDoctors();

            return CollectionResponse.of(doctors);
        }
    }
}
