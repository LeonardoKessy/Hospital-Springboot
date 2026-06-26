CREATE TABLE users(
    id UUID DEFAULT UUID() PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    identifier_value VARCHAR(50),
    identifier_type VARCHAR(50),
    email VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL,

    CONSTRAINT uq_user_identifier
    UNIQUE (identifier_value, identifier_type),

    CONSTRAINT uq_user_email
    UNIQUE (email)
);

CREATE TABLE user_roles(
    user_id UUID NOT NULL,
    user_role VARCHAR(50) NOT NULL,

    PRIMARY KEY (user_id, user_role),

    CONSTRAINT fk_user_id_roles
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);

CREATE TABLE employees(
    id UUID DEFAULT UUID() PRIMARY KEY,
    user_id UUID NOT NULL,
    enterprise_email VARCHAR(50) NOT NULL,
    contract_type VARCHAR(50) NOT NULL,
    base_salary DECIMAL(12, 2) NOT NULL,
    salary_currency CHAR(3) NOT NULL DEFAULT 'USD',
    status VARCHAR(50) NOT NULL,
    hire_date DATE NOT NULL,
    termination_date DATE
);

ALTER TABLE doctors
DROP COLUMN first_name,
DROP COLUMN last_name,
DROP COLUMN identifier_value,
DROP COLUMN identifier_type,
DROP COLUMN hire_date,
DROP COLUMN contract_type,
DROP COLUMN salary_currency,
DROP COLUMN salary_amount,
DROP COLUMN status;

ALTER TABLE doctors
ADD CONSTRAINT fk_employee_id
FOREIGN KEY (id)
REFERENCES employees(id)
