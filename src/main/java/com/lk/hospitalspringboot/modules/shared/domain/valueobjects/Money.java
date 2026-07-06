package com.lk.hospitalspringboot.modules.shared.domain.valueobjects;

import com.lk.hospitalspringboot.modules.shared.domain.enums.ValidCurrencies;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.BusinessRuleException;
import com.lk.hospitalspringboot.modules.shared.domain.exceptions.InputValidationException;
import com.lk.hospitalspringboot.modules.staff.domain.exceptions.StaffBusinessRules;

import java.math.BigDecimal;

public record Money(
        BigDecimal amount,
        ValidCurrencies currency
) {
    public Money {
        if (amount == null) {
            throw new InputValidationException("amount", "Amount must not be null");
        }
        if (currency == null) {
            throw new InputValidationException("currency", "Currency cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRuleException(StaffBusinessRules.NO_NEGATIVE_SALARY);
        }
    }

    public static Money usd(BigDecimal amount) {
        return new Money(amount, ValidCurrencies.USD);
    }

    public static Money eur(BigDecimal amount) {
        return new Money(amount, ValidCurrencies.EUR);
    }

    public static Money ars(BigDecimal amount) {
        return new Money(amount, ValidCurrencies.ARS);
    }

    public String money() {
        return amount.toString() + " " +  currency.name();
    }
}
