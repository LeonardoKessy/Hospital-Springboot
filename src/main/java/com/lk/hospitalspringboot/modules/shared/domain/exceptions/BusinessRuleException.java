package com.lk.hospitalspringboot.modules.shared.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.interfaces.BusinessRuleErrorCode;
import lombok.Getter;

@Getter
public class BusinessRuleException extends RuntimeException {
    private final String code;

    public BusinessRuleException(
            BusinessRuleErrorCode errorCode
    ) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
}
