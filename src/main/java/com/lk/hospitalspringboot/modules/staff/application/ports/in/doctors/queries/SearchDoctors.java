package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries;

import com.lk.hospitalspringboot.modules.shared.application.ports.in.responses.CollectionResponse;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.utils.EnumParser;
import com.lk.hospitalspringboot.modules.shared.domain.utils.InputValidator;
import com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries.responses.DoctorSummaryResponse;
import com.lk.hospitalspringboot.modules.staff.application.services.doctors.DoctorRepository;
import lombok.RequiredArgsConstructor;

public final class SearchDoctors {

    public static record Query(
            String name,
            String specialty,
            int page,
            int size
    ) {
        public Query {
            InputValidator.initialize()
                    .ensure(() -> page < 0, "page", "Page index cannot be negative")
                    .ensure(() -> size <= 0, "size", "Page size must be greater than zero")
                    .ensure(
                            () -> specialty != null && !EnumParser.isValid(MedicalSpecialty.class, specialty),
                            "specialty", "Invalid specialty")
                    .validate();
        }

        public MedicalSpecialty medicalSpecialty() {
            return EnumParser.parse(MedicalSpecialty.class, specialty);
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public CollectionResponse<DoctorSummaryResponse> execute(Query query) {
            var doctors = doctorRepository.searchDoctors(query);

            return CollectionResponse.of(doctors, query.page, query.size);
        }
    }
}
