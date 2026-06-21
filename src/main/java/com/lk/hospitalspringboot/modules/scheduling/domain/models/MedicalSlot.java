package com.lk.hospitalspringboot.modules.scheduling.domain.models;

import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
public class MedicalSlot {
    private final Long id;
    private final UUID doctorId;
    private final MedicalSpecialty specialty;
    private final Instant appointmentTime;
    private final int durationInMinutes;

    public static final int MINIMUM_DURATION_IN_MINUTES = 10;
    public static final int MAXIMUM_DURATION_IN_MINUTES = 120;

    public MedicalSlot(
            Long id,
            UUID doctorId,
            MedicalSpecialty specialty,
            Instant appointmentTime,
            int durationInMinutes
    ) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.doctorId = Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
        this.specialty = Objects.requireNonNull(specialty, "Specialty cannot be null");
        this.appointmentTime = Objects.requireNonNull(appointmentTime, "Appointment time must not be null");


        if (durationInMinutes <= MINIMUM_DURATION_IN_MINUTES || durationInMinutes > MAXIMUM_DURATION_IN_MINUTES)
            throw new IllegalArgumentException("Slot duration must be between 1 and 120 minutes");
        this.durationInMinutes = durationInMinutes;
    }

    public static MedicalSlot create(
            Long id,
            UUID doctorId,
            MedicalSpecialty medicalSpecialty,
            Instant appointmentTime,
            int durationInMinutes,
            Instant currentSystemTime
    ) {
        if (appointmentTime.isBefore(currentSystemTime)) {
            throw new IllegalArgumentException("Appointment time must be before current system time");
        }
        return new MedicalSlot(id, doctorId, medicalSpecialty, appointmentTime, durationInMinutes);
    }
}
