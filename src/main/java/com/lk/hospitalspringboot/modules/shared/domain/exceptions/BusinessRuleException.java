package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.interfaces.BusinessRuleErrorCode;

public class BusinessRuleException extends RuntimeException {
    private final BusinessRuleErrorCode errorCode;

    public BusinessRuleException(
            BusinessRuleErrorCode errorCode
    ) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
