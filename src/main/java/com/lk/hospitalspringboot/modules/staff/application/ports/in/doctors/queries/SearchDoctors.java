package com.lk.hospitalspringboot.modules.staff.application.ports.in.doctors.queries;

import com.lk.hospitalspringboot.modules.shared.application.ports.in.responses.CollectionResponse;
import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import com.lk.hospitalspringboot.modules.shared.domain.utils.TypeParser;
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
                    .ensure(() -> page >= 0, "page", "Page index cannot be negative")
                    .ensure(() -> size > 0, "size", "Page size must be greater than zero")
                    .ensure(
                            () -> specialty == null || InputValidator.isValidEnum(MedicalSpecialty.class, specialty),
                            "specialty", "Invalid specialty")
                    .validate();
        }

        public MedicalSpecialty medicalSpecialty() {
            return TypeParser.parseEnum(MedicalSpecialty.class, specialty);
        }
    }

    @RequiredArgsConstructor
    public static class Handler {
        private final DoctorRepository doctorRepository;

        public CollectionResponse<DoctorSummaryResponse> execute(Query query) {
            var doctors = doctorRepository.searchDoctors(query);

            var dtos = doctors.stream()
                    .map(DoctorSummaryResponse::from)
                    .toList();

            return CollectionResponse.of(dtos, query.page, query.size);
        }
    }
}
