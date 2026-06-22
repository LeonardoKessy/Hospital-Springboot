CREATE TABLE patients(
    id UUID DEFAULT UUID() PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    identifier_type VARCHAR(50) NOT NULL,
    identifier_value VARCHAR(50) NOT NULL,
    street_address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,

    CONSTRAINT uq_patient_identifier
    UNIQUE (identifier_type, identifier_value)
);

CREATE TABLE doctors(
    id UUID DEFAULT UUID() PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    identifier_type VARCHAR(50) NOT NULL,
    identifier_value VARCHAR(50) NOT NULL,
    medical_license varchar(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,

    CONSTRAINT uq_doctor_identifier
    UNIQUE (identifier_type, identifier_value)
);

CREATE TABLE doctor_specialties(
    doctor_id UUID NOT NULL,
    specialty VARCHAR(50) NOT NULL,

    PRIMARY KEY (doctor_id, specialty),

    CONSTRAINT fk_doctor_id_specialties
    FOREIGN KEY (doctor_id)
    REFERENCES doctors(id)
);

CREATE TABLE medical_slots(
    id UUID DEFAULT UUID() PRIMARY KEY,
    doctor_id UUID NOT NULL,
    specialty VARCHAR(50) NOT NULL,
    appointment_time TIMESTAMP NOT NULL,
    duration_in_minutes int NOT NULL,
    timezone VARCHAR(100) NOT NULL DEFAULT 'America/Argentina/Buenos_Aires',

    CONSTRAINT fk_doctor_id_slots
    FOREIGN KEY (doctor_id)
    REFERENCES doctors(id)
);

CREATE TABLE appointments(
    id UUID DEFAULT UUID() PRIMARY KEY,
    patient_id UUID NOT NULL,
    doctor_id UUID NOT NULL,
    slot_id UUID,
    specialty VARCHAR(50),
    appointment_time TIMESTAMP NOT NULL,
    duration_in_minutes int NOT NULL,
    timezone VARCHAR(100) NOT NULL DEFAULT 'America/Argentina/Buenos_Aires',
    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_patient_id_appointments
    FOREIGN KEY (patient_id)
    REFERENCES patients(id),

    CONSTRAINT fk_doctor_id_appointments
    FOREIGN KEY (doctor_id)
    REFERENCES doctors(id),

    CONSTRAINT fk_slot_id_appointments
    FOREIGN KEY (slot_id)
    REFERENCES medical_slots(id)
);
