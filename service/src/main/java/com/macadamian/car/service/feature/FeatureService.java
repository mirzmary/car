package com.macadamian.car.service.feature;

import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.feature.dto.FeatureCreationDto;
import com.macadamian.car.service.feature.dto.FeatureUpdateDto;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 4:06 PM
 */
public interface FeatureService {

    List<Feature> getMainFeatureList();

    Feature getById(final Long id);

    ValidationResult validateFeatureCreationDto(FeatureCreationDto dto);

    ValidationResult validateFeatureUpdateDto(FeatureUpdateDto dto);

    Feature create(final FeatureCreationDto featureCreationDto);

    Feature update(final FeatureUpdateDto featureUpdateDto);
}
