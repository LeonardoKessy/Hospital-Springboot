package com.lk.hospitalspringboot.modules.scheduling.domain.models;

import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;
import lombok.Getter;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Appointment {
    private final UUID id;
    private final UUID patientId;
    private final UUID doctorId;
    private final UUID slotId;
    private final MedicalSpecialty specialty;
    private final Instant appointmentTime;
    private final int durationInMinutes;
    private final ZoneId timezone;
    private AppointmentStatus status;

    private static final int MAX_CANCELLATION_TIME_VALUE = 2;
    private static final TemporalUnit MAX_CANCELLATION_TIME_UNIT = ChronoUnit.HOURS;

    public enum AppointmentStatus {
        BOOKED, CANCELED, COMPLETED
    }

    public Appointment(
        UUID id,
        UUID patientId,
        UUID doctorId,
        UUID slotId,
        MedicalSpecialty specialty,
        Instant appointmentTime,
        int durationInMinutes,
        ZoneId timezone,
        AppointmentStatus status
    ) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.patientId = Objects.requireNonNull(patientId, "Patient ID cannot be null");
        this.doctorId = Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
        this.slotId = Objects.requireNonNull(slotId, "Slot ID cannot be null");
        this.specialty = Objects.requireNonNull(specialty, "Medical Specialty cannot be null");
        this.appointmentTime = Objects.requireNonNull(appointmentTime, "Appointment Time cannot be null");

        if (durationInMinutes <= MedicalSlot.MINIMUM_DURATION_IN_MINUTES || durationInMinutes > MedicalSlot.MAXIMUM_DURATION_IN_MINUTES)
            throw new IllegalArgumentException("Duration cannot be less than 1 minute");
        this.durationInMinutes = durationInMinutes;

        this.timezone = Objects.requireNonNull(timezone, "Timezone cannot be null");

        this.status = Objects.requireNonNull(status, "Appointment Status cannot be null");
    }

    public static Appointment create(
            MedicalSlot medicalSlot,
            UUID appointmentId,
            UUID patientId,
            Clock clock
    ) {
        if (medicalSlot == null) {
            throw new IllegalArgumentException("Medical Slot cannot be null");
        }

        ZonedDateTime now = ZonedDateTime.now(clock.withZone(medicalSlot.getTimezone()));
        ZonedDateTime appointmentTime = medicalSlot.getAppointmentTime().atZone(medicalSlot.getTimezone());
        if (appointmentTime.isBefore(now)) {
            throw new IllegalArgumentException("Cannot create appointment with an expired slot");
        }

        return new Appointment(
                appointmentId,
                patientId,
                medicalSlot.getDoctorId(),
                medicalSlot.getId(),
                medicalSlot.getSpecialty(),
                medicalSlot.getAppointmentTime(),
                medicalSlot.getDurationInMinutes(),
                medicalSlot.getTimezone(),
                AppointmentStatus.BOOKED
        );
    }

    public void cancel(Clock clock) {
        if (status != AppointmentStatus.BOOKED) {
            throw new IllegalStateException("Cannot cancel an appointment that is not BOOKED");
        }

        Instant now = clock.instant();
        Instant maxCancellationTime = appointmentTime.minus(MAX_CANCELLATION_TIME_VALUE, MAX_CANCELLATION_TIME_UNIT);
        if (now.isAfter(maxCancellationTime)) {
            throw new IllegalStateException("Cannot cancel appointment if appointment so close to it's stated time");
        }

        this.status = AppointmentStatus.CANCELED;
    }

    public void complete() {
        if (status == AppointmentStatus.BOOKED)
            this.status = AppointmentStatus.COMPLETED;
        else
            throw new IllegalStateException("Cannot complete appointment if appointment status is not BOOKED");
    }
}
