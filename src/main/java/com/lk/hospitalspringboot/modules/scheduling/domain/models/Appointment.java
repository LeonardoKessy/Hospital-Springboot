package com.lk.hospitalspringboot.modules.scheduling.domain.models;

import com.lk.hospitalspringboot.modules.scheduling.domain.valueobjects.MedicalSpecialty;
import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Appointment {
    private final UUID id;
    private final UUID patientId;
    private final UUID doctorId;
    private final Long slotId;
    private final MedicalSpecialty specialty;
    private final Instant appointmentTime;
    private final int durationInMinutes;
    private AppointmentStatus  appointmentStatus;

    private static final int MAX_CANCELLATION_TIME_VALUE = 2;
    private static final TemporalUnit MAX_CANCELLATION_TIME_UNIT = ChronoUnit.HOURS;
    private final Instant maxCancellationTime;

    public enum AppointmentStatus {
        BOOKED, CANCELED, COMPLETED
    }

    public Appointment(
        UUID id,
        UUID patientId,
        UUID doctorId,
        Long slotId,
        MedicalSpecialty medicalSpecialty,
        Instant appointmentTime,
        int durationInMinutes,
        AppointmentStatus appointmentStatus
    ) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.patientId = Objects.requireNonNull(patientId, "Patient ID cannot be null");
        this.doctorId = Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
        this.slotId = Objects.requireNonNull(slotId, "Slot ID cannot be null");
        this.specialty = Objects.requireNonNull(medicalSpecialty, "Medical Specialty cannot be null");
        this.appointmentTime = Objects.requireNonNull(appointmentTime, "Appointment Time cannot be null");

        if (durationInMinutes <= MedicalSlot.MINIMUM_DURATION_IN_MINUTES || durationInMinutes > MedicalSlot.MAXIMUM_DURATION_IN_MINUTES)
            throw new IllegalArgumentException("Duration cannot be less than 1 minute");
        this.durationInMinutes = durationInMinutes;

        this.appointmentStatus = Objects.requireNonNull(appointmentStatus, "Appointment Status cannot be null");

        this.maxCancellationTime = appointmentTime.minus(MAX_CANCELLATION_TIME_VALUE, MAX_CANCELLATION_TIME_UNIT);
    }

    public static Appointment create(
            MedicalSlot medicalSlot,
            UUID appointmentId,
            UUID patientId,
            Instant now
    ) {
        if (medicalSlot == null) {
            throw new IllegalArgumentException("Medical Slot cannot be null");
        }

        if (medicalSlot.getAppointmentTime().isBefore(now)) {
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
                AppointmentStatus.BOOKED
        );
    }

    public void cancel(Instant now) {
        if (appointmentStatus != AppointmentStatus.BOOKED) {
            throw new IllegalStateException("Cannot cancel an appointment that is not BOOKED");
        }

        if (now.isAfter(maxCancellationTime)) {
            throw new IllegalArgumentException("Cannot cancel appointment if appointment so close to it's stated time");
        }

        this.appointmentStatus = AppointmentStatus.CANCELED;
    }

    public void complete() {
        if (appointmentStatus == AppointmentStatus.BOOKED)
            this.appointmentStatus = AppointmentStatus.COMPLETED;
        else
            throw new IllegalStateException("Cannot complete appointment if appointment status is not BOOKED");
    }
}
