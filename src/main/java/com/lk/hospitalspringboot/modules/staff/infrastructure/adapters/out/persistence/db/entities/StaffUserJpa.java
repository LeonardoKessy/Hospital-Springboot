package com.lk.hospitalspringboot.modules.staff.infrastructure.adapters.out.persistence.db.entities;

import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import com.lk.hospitalspringboot.modules.staff.domain.valueobjects.UserRecord;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
public class StaffUserJpa {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "identifier_type", nullable = false, length = 50)
    private NationalIdentifier.IdentifierType identifierType;

    @Column(name = "identifier_value", nullable = false, length = 50)
    private String identifierValue;

    public UserRecord toDomain() {
        return new UserRecord(
                this.id,
                new HumanName(
                        this.firstName,
                        this.lastName
                ),
                new NationalIdentifier(
                        this.identifierType,
                        this.identifierValue
                )
        );
    }
}