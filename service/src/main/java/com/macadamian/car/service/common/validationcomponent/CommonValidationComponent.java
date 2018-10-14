package com.macadamian.car.service.common.validationcomponent;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CommonValidationComponent {

    boolean validateId(final Long id);

    boolean validateYear(Integer year);

    boolean validateString(final String str);

    boolean validateAmount(final BigDecimal amount);
}
