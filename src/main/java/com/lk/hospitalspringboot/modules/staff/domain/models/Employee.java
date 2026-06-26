package com.lk.hospitalspringboot.modules.staff.domain.models;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.BusinessRuleException;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.Money;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.exceptions.StaffBusinessRules;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Employee {
    private final UUID id;
    private final UUID userId;
    private final HumanName name;
    private final NationalIdentifier identifier;
    private String enterpriseEmail;
    private final LocalDate hireDate;
    private LocalDate terminationDate;
    private EmployeeStatus status;
    private ContractType contractType;
    private Money salary;

     public Employee(
            UUID id,
            UUID userId,
            HumanName name,
            NationalIdentifier identifier,
            String enterpriseEmail,
            LocalDate hireDate,
            LocalDate terminationDate,
            ContractType contractType,
            Money salary,
            EmployeeStatus status
    ) {
        this.id = Objects.requireNonNull(id, "ID must not be null");
        this.userId = Objects.requireNonNull(userId, "User ID must not be null");
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.identifier = Objects.requireNonNull(identifier, "Identifier must not be null");

        this.enterpriseEmail = Objects.requireNonNull(enterpriseEmail, "Enterprise email must not be null");

        this.hireDate = Objects.requireNonNull(hireDate, "Hire date must not be null");

        if (terminationDate != null && terminationDate.isBefore(hireDate)) {
            throw new BusinessRuleException(StaffBusinessRules.TERMINATION_BEFORE_HIRE);
        }
        this.terminationDate = terminationDate;

        this.contractType = Objects.requireNonNull(contractType, "Contract type must not be null");
        this.salary = Objects.requireNonNull(salary, "Salary must not be null");

        this.status = Objects.requireNonNull(status, "Status must not be null");
    }

    public static Employee hire(
            UUID id,
            UUID userId,
            HumanName name,
            NationalIdentifier identifier,
            String enterpriseEmail,
            ContractType contractType,
            Money salary
    ) {
         return new Employee(
                 id,
                 userId,
                 name,
                 identifier,
                 enterpriseEmail,
                 LocalDate.now(),
                 null,
                 contractType,
                 salary,
                 EmployeeStatus.ACTIVE
         );
    }

    public void setContractType(ContractType contractType) {
        this.contractType = Objects.requireNonNull(contractType, "Contract type must not be null");
    }

    public void setSalary(Money salary) {
        this.salary = Objects.requireNonNull(salary, "Salary must not be null");
    }

    public void setEnterpriseEmail(String enterpriseEmail) {
         this.enterpriseEmail = Objects.requireNonNull(enterpriseEmail, "Enterprise email must not be null");
    }

    public void activate() {
        if (
                this.status == EmployeeStatus.ACTIVE ||
                        this.status == EmployeeStatus.TERMINATED
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.ACTIVE;
    }

    public void suspend() {
        if (
                this.status == EmployeeStatus.SUSPENDED ||
                        this.status == EmployeeStatus.TERMINATED ||
                        this.status == EmployeeStatus.ON_LEAVE
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.SUSPENDED;
    }

    public void absent() {
        if (
                this.status == EmployeeStatus.SUSPENDED ||
                        this.status == EmployeeStatus.TERMINATED ||
                        this.status == EmployeeStatus.ON_LEAVE
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.ON_LEAVE;
    }

    public void terminate() {
        if (
                this.status == EmployeeStatus.TERMINATED
        ) {
            throw new BusinessRuleException(StaffBusinessRules.IMPOSSIBLE_STATUS_CHANGE);
        }
        this.status = EmployeeStatus.TERMINATED;
    }
}
