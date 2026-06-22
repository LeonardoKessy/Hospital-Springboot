package com.lk.hospitalspringboot.modules.scheduling.domain.models;

import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanAddress;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.HumanName;
import com.lk.hospitalspringboot.modules.shared.domain.valueobjects.NationalIdentifier;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Patient {
    private final UUID id;
    private HumanName name;
    private final NationalIdentifier nationalIdentifier;
    private HumanAddress address;
    private String contractedPlan;

    public Patient(
            UUID id,
            HumanName name,
            NationalIdentifier nationalIdentifier,
            HumanAddress address,
            String contractedPlan) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.nationalIdentifier = Objects.requireNonNull(nationalIdentifier);
        this.address = Objects.requireNonNull(address);

        if (contractedPlan == null || contractedPlan.isBlank())
            throw new IllegalArgumentException("Contracted Plan must not be null or blank");
        this.contractedPlan = contractedPlan;
    }

    public void setName(HumanName name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public void setAddress(HumanAddress address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        this.address = address;
    }

    public void setPlan(String contractedPlan) {
        if (contractedPlan == null || contractedPlan.isEmpty()) {
            throw new IllegalArgumentException("Contracted Plan cannot be null or empty");
        }
        this.contractedPlan = contractedPlan;
    }
}
