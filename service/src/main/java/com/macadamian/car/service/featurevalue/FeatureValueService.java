package com.macadamian.car.service.featurevalue;

import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.feature.dto.FeatureCreationDto;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 7:09 PM
 */
public interface FeatureValueService {

    FeatureValue getById(final Long id);
}
