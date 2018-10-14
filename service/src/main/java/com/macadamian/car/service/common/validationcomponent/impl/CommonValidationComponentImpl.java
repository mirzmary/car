package com.macadamian.car.service.common.validationcomponent.impl;

import com.macadamian.car.service.common.validationcomponent.CommonValidationComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@Qualifier("commonValidationComponentImpl")
public class CommonValidationComponentImpl implements CommonValidationComponent {

    @Override
    public boolean validateId(final Long id) {
        return Optional.ofNullable(id)
                .filter(aLong -> aLong > 0L)
                .isPresent();
    }

    @Override
    public boolean validateYear(final Integer year) {
        return Optional.ofNullable(year)
                .filter(y -> y > 0)
                .isPresent();
    }

    @Override
    public boolean validateString(final String str) {
        return Optional.ofNullable(str)
                .filter(s -> s.trim().length() > 0)
                .isPresent();
    }

    @Override
    public boolean validateAmount(final BigDecimal amount) {
        return Optional.ofNullable(amount)
                .filter(a -> a.compareTo(BigDecimal.ZERO) > 0)
                .isPresent();
    }
}
