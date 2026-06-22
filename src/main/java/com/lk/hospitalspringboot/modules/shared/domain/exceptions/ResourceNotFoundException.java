package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final ResourceType resourceType;
    private final Object resourceId;

    public ResourceNotFoundException(ResourceType resourceType, Object resourceId) {
        super(String.format("%s with id %s not found", resourceType.toString(), resourceId.toString()));
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }
}
