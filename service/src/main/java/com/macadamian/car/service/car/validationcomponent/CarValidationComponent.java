package com.macadamian.car.service.car.validationcomponent;

import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.common.validationcomponent.CommonValidationComponent;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 6:31 PM
 */
public interface CarValidationComponent extends CommonValidationComponent {

    ValidationResult validateCarCreationDto(final CarCreationDto carCreationDto);
}
