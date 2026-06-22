package com.lk.hospitalspringboot.modules.scheduling.domain.models;

import com.lk.hospitalspringboot.modules.shared.domain.enums.MedicalSpecialty;
import lombok.Getter;

import java.time.*;
import java.util.Objects;
import java.util.UUID;

@Getter
public class MedicalSlot {
    private final UUID id;
    private final UUID doctorId;
    private final MedicalSpecialty specialty;
    private final Instant appointmentTime;
    private final int durationInMinutes;
    private final ZoneId timezone;

    public static final int MINIMUM_DURATION_IN_MINUTES = 10;
    public static final int MAXIMUM_DURATION_IN_MINUTES = 120;

    public MedicalSlot(
            UUID id,
            UUID doctorId,
            MedicalSpecialty specialty,
            Instant appointmentTime,
            int durationInMinutes,
            ZoneId timezone
    ) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.doctorId = Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
        this.specialty = Objects.requireNonNull(specialty, "Specialty cannot be null");
        this.appointmentTime = Objects.requireNonNull(appointmentTime, "Appointment time must not be null");
        this.timezone = Objects.requireNonNull(timezone, "Timezone must not be null");

        if (durationInMinutes <= MINIMUM_DURATION_IN_MINUTES || durationInMinutes > MAXIMUM_DURATION_IN_MINUTES)
            throw new IllegalArgumentException("Slot duration must be between 1 and 120 minutes");
        this.durationInMinutes = durationInMinutes;
    }

    public static MedicalSlot create(
            UUID id,
            UUID doctorId,
            MedicalSpecialty medicalSpecialty,
            Instant appointmentTime,
            int durationInMinutes,
            ZoneId timezone,
            Clock clock
    ) {
        Instant now = clock.instant();
        if (appointmentTime.isBefore(now)) {
            throw new IllegalArgumentException("Appointment time cannot be in the past");
        }

        return new MedicalSlot(id, doctorId, medicalSpecialty, appointmentTime, durationInMinutes, timezone);
    }
}
