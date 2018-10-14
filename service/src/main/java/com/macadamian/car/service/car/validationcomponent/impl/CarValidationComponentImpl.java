package com.macadamian.car.service.car.validationcomponent.impl;

import com.macadamian.car.persistence.common.entity.AbstractDomainEntityModel;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.FeatureDto;
import com.macadamian.car.service.car.validationcomponent.CarValidationComponent;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.common.enums.Error;
import com.macadamian.car.service.common.validationcomponent.impl.CommonValidationComponentImpl;
import com.macadamian.car.service.feature.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 6:31 PM
 */
@Service
@Qualifier("carValidationComponentImpl")
public class CarValidationComponentImpl extends CommonValidationComponentImpl implements CarValidationComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarValidationComponentImpl.class);

    @Autowired
    private FeatureService featureService;

    @Override
    public ValidationResult validateCarCreationDto(final CarCreationDto carCreationDto) {
        Assert.notNull(carCreationDto, "carCreationDto can not be null when creating a car");
        ValidationResult validationResult = new ValidationResult();
        if (!validateId(carCreationDto.getBrandId())) {
            LOGGER.info("Can not create car when Brand id is invalid.");
            validationResult.put(Error.INVALID_CAR_BRAND, String.format("Can not create car when Brand id is invalid - %s.", carCreationDto.getBrandId()));
        }
        if (!validateId(carCreationDto.getModelId())) {
            LOGGER.info("Can not create car when Model id is invalid.");
            validationResult.put(Error.INVALID_CAR_MODEL, String.format("Can not create car when Model id is invalid - %s.", carCreationDto.getModelId()));
        }
        if (!validateId(carCreationDto.getColorId())) {
            LOGGER.info("Can not create car when Color id is invalid.");
            validationResult.put(Error.INVALID_CAR_COLOR, String.format("Can not create car when Color id is invalid - %s.", carCreationDto.getColorId()));
        }
        if (!validateYear(carCreationDto.getYear())) {
            LOGGER.info("Can not create car when Year id is invalid.");
            validationResult.put(Error.INVALID_CAR_YEAR, String.format("Can not create car when Year id is invalid - %s.", carCreationDto.getYear()));
        }
        if (!validateAmount(carCreationDto.getPrice())) {
            LOGGER.info("Can not create car when Price id is invalid.");
            validationResult.put(Error.INVALID_CAR_PRICE, String.format("Can not create car when Price id is invalid - %s.", carCreationDto.getPrice()));
        }
        if (carCreationDto.getFeatures() == null || carCreationDto.getFeatures().isEmpty()) {
            LOGGER.info("Can not create car with no Features.");
            validationResult.put(Error.INVALID_CAR_FEATURES, "Can not create car with no Features");
        } else {
            final List<Long> mainFeatureList = featureService.getMainFeatureList().stream().map(AbstractDomainEntityModel::getId).collect(Collectors.toList());
            final List<Long> featureIds = new ArrayList<>(carCreationDto.getFeatures().stream().map(FeatureDto::getFeatureId).collect(Collectors.toList()));
            if (!featureIds.containsAll(mainFeatureList)) {
                LOGGER.info("Can not create car with not all main Features set.");
                validationResult.put(Error.INVALID_CAR_FEATURES, String.format("Can not create car with not all main Features set, missing feature ids - %s", mainFeatureList.removeAll(featureIds)));
            }
        }
        return validationResult;
    }
}
