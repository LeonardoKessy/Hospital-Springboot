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
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class StaffEmployeeJpa {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
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

    public static StaffEmployeeJpa from(Employee employee, StaffUserJpa user) {
        return new StaffEmployeeJpa(
            employee.getId(),
            user,
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