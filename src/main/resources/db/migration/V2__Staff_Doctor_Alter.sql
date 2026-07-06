ALTER TABLE doctors
DROP COLUMN active;

ALTER TABLE doctors
ADD COLUMN hire_date DATE,
ADD COLUMN contract_type VARCHAR(50),
ADD COLUMN salary_amount DECIMAL(12, 2),
ADD COLUMN salary_currency CHAR(3),
ADD COLUMN status VARCHAR(50),
ADD CONSTRAINT uq_doctor_medical_license
UNIQUE (medical_license);