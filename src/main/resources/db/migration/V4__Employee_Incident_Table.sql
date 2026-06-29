CREATE TABLE employee_incidents(
    id UUID,
    employeeId UUID,
    type VARCHAR(50),
    reason VARCHAR(255),

    CONSTRAINT fk_incidents_employee_id
    FOREIGN KEY (employeeId)
    REFERENCES employees(id)
);