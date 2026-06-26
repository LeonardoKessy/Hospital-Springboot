package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities;

import com.lk.hospitalspringboot.modules.shared.domain.enums.EmployeeStatus;
import com.lk.hospitalspringboot.modules.shared.domain.enums.ValidCurrencies;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.Money;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import com.lk.hospitalspringboot.modules.staff.domain.enums.ContractType;
import com.lk.hospitalspringboot.modules.staff.domain.models.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class StaffEmployeeJpa {

    private StaffEmployeeJpa(
            UUID id,
            UUID userId,
            String enterpriseEmail,
            LocalDate hireDate,
            LocalDate terminationDate,
            ContractType contractType,
            BigDecimal baseSalary,
            ValidCurrencies salaryCurrency,
            EmployeeStatus status
    ) {
        this.id = id;
        this.userId = userId;
        this.enterpriseEmail = enterpriseEmail;
        this.hireDate = hireDate;
        this.terminationDate = terminationDate;
        this.contractType = contractType;
        this.baseSalary = baseSalary;
        this.salaryCurrency = salaryCurrency;
        this.status = status;
    }

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private StaffUserJpa user;

    @Column(name = "enterprise_email", nullable = false)
    private String enterpriseEmail;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "termination_date", nullable = false)
    private LocalDate terminationDate;

    @Column(name = "contract_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Column(name = "base_salary", nullable = false)
    private BigDecimal baseSalary;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_currency", nullable = false)
    private ValidCurrencies salaryCurrency;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    public Employee toDomain() {
        return new Employee(
            this.id,
            this.user.getId(),
            new HumanName(
                    this.user.getFirstName(),
                    this.user.getLastName()
            ),
            new NationalIdentifier(
                    this.user.getIdentifierType(),
                    this.user.getIdentifierValue()
            ),
            this.enterpriseEmail,
            this.hireDate,
            this.terminationDate,
            this.contractType,
            new Money(
                    this.baseSalary,
                    this.salaryCurrency
            ),
            this.status
        );
    }

    public static StaffEmployeeJpa from(Employee employee) {
        return new StaffEmployeeJpa(
            employee.getId(),
            employee.getUserId(),
            employee.getEnterpriseEmail(),
            employee.getHireDate(),
            employee.getTerminationDate(),
            employee.getContractType(),
            employee.getSalary().amount(),
            employee.getSalary().currency(),
            employee.getStatus()
        );
    }
}