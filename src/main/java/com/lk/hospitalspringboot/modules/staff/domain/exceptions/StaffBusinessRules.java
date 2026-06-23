package com.lk.hospitalspringboot.modules.staff.domain.exceptions;

import com.lk.hospitalspringboot.modules.shared.domain.interfaces.BusinessRuleErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StaffBusinessRules implements BusinessRuleErrorCode {
    INVALID_LICENSE("INVALID_LICENSE", "A doctor must have a valid medical license"),
    NO_EMPTY_SPECIALTIES("NO_EMPTY_SPECIALTIES", "A doctor must have at least one specialty"),
    NO_NEGATIVE_SALARY("NO_NEGATIVE_SALARY", "No member of the staff can have a negative salary"),
    IMPOSSIBLE_STATUS_CHANGE("IMPOSSIBLE_STATUS_CHANGE", "Status cannot be changed due to state change restrictions");

    private final String code;
    private final String message;
}
