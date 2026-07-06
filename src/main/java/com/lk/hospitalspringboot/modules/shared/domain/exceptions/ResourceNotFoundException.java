package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ResourceType;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final ResourceType resourceType;
    private final Object resourceIdentifier;
    private final String code = "RESOURCE_NOT_FOUND";


    public ResourceNotFoundException(ResourceType resourceType, Object resourceId) {
        super(String.format("%s with field %s not found", resourceType.toString(), resourceId.toString()));
        this.resourceType = resourceType;
        this.resourceIdentifier = resourceId;
    }
}
