package com.macadamian.car.service.feature.impl;

import com.macadamian.car.persistence.feature.FeatureRepository;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.featurevalue.FeatureValueRepository;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.common.enums.Error;
import com.macadamian.car.service.common.exception.LoggerAwareServiceRuntimeException;
import com.macadamian.car.service.common.exception.ValidationFailedServiceRuntimeException;
import com.macadamian.car.service.common.validationcomponent.CommonValidationComponent;
import com.macadamian.car.service.feature.FeatureService;
import com.macadamian.car.service.feature.dto.FeatureCreationDto;
import com.macadamian.car.service.feature.dto.FeatureUpdateDto;
import com.macadamian.car.service.feature.dto.FeatureValueDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 4:06 PM
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureServiceImpl.class);

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    @Qualifier("commonValidationComponentImpl")
    private CommonValidationComponent commonValidationComponent;

    @Autowired
    private FeatureValueRepository featureValueRepository;

    @Override
    public List<Feature> getMainFeatureList() {
        return featureRepository.findByMain(true);
    }

    @Override
    public Feature getById(final Long id) {
        return Optional.ofNullable(featureRepository.findOne(id))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "No feature found for id - %s", id));
    }

    @Override
    public ValidationResult validateFeatureCreationDto(final FeatureCreationDto dto) {
        Assert.notNull(dto, "featureCreationDto should not be null when creating a feature with its value");
        final ValidationResult validationResult = new ValidationResult();
        if(!commonValidationComponent.validateString(dto.getName())) {
            LOGGER.info("Can not create feature with invalid name.");
            validationResult.put(Error.INVALID_FEATURE_NAME, String.format("Can not create feature with invalid name - %s.", dto.getName()));
        }
        if(dto.getValues() == null || dto.getValues().isEmpty()) {
            LOGGER.info("Can not create feature with no available values");
            validationResult.put(Error.INVALID_FEATURE_VALUES, "Can not create feature with no available values");
        }
        else {
            for(String value : dto.getValues()) {
                if(!commonValidationComponent.validateString(value)) {
                    LOGGER.info("Can not create feature with invalid value.");
                    validationResult.put(Error.INVALID_FEATURE_VALUES, String.format("Can not create feature with invalid value - %s.", value));
                }
            }
        }
        return validationResult;
    }

    @Override
    public ValidationResult validateFeatureUpdateDto(final FeatureUpdateDto dto) {
        Assert.notNull(dto, "FeatureUpdateDto should not be null when creating a feature with its value");
        final ValidationResult validationResult = new ValidationResult();
        if(!commonValidationComponent.validateId(dto.getId())) {
            LOGGER.info("Can not update feature with invalid id.");
            validationResult.put(Error.INVALID_FEATURE_ID, String.format("Can not create feature with invalid id - %s.", dto.getId()));
        }
        if(!commonValidationComponent.validateString(dto.getName())) {
            LOGGER.info("Can not update feature with invalid name.");
            validationResult.put(Error.INVALID_FEATURE_NAME, String.format("Can not update feature with invalid name - %s.", dto.getName()));
        }
        if(dto.getFeatureValueDtos() == null || dto.getFeatureValueDtos().isEmpty()) {
            LOGGER.info("Can not update feature with no available values");
            validationResult.put(Error.INVALID_FEATURE_VALUES, "Can not update feature with no available values");
        }
        else {
            for(FeatureValueDto value : dto.getFeatureValueDtos()) {
                if(!commonValidationComponent.validateString(value.getValue())) {
                    LOGGER.info("Can not update feature with invalid value.");
                    validationResult.put(Error.INVALID_FEATURE_VALUES, String.format("Can not update feature with invalid value - %s.", value));
                }
            }
        }
        return validationResult;
    }

    @Override
    @Transactional
    public Feature create(final FeatureCreationDto featureCreationDto) {
        Assert.notNull(featureCreationDto, "featureCreationDto should not be null when creating a feature with its value");
        final ValidationResult validationResult = validateFeatureCreationDto(featureCreationDto);
        if(!validationResult.isValid()) {
            throw new ValidationFailedServiceRuntimeException(this, validationResult.getMessages().get(0));
        }
        final Feature feature = new Feature(featureCreationDto.getName(), featureCreationDto.isMain());
        featureRepository.save(feature);
        final List<FeatureValue> values = createFeatureValues(featureCreationDto, feature);
        feature.setFeatureValues(values);
        return feature;
    }

    @Override
    public Feature update(final FeatureUpdateDto featureUpdateDto) {
        Assert.notNull(featureUpdateDto, "featureUpdateDto should not be null when updating a feature with its value");
        final ValidationResult validationResult = validateFeatureUpdateDto(featureUpdateDto);
        if(!validationResult.isValid()) {
            throw new ValidationFailedServiceRuntimeException(this, validationResult.getMessages().get(0));
        }
        final Feature feature = featureRepository.findOne(featureUpdateDto.getId());
        feature.setName(featureUpdateDto.getName());
        feature.setMain(featureUpdateDto.isMain());
        featureRepository.save(feature);
        final List<FeatureValue> values = updateFeatureValues(featureUpdateDto, feature);
        feature.setFeatureValues(values);
        return feature;
    }

    private List<FeatureValue> createFeatureValues(final FeatureCreationDto featureCreationDto, final Feature feature) {
        final List<FeatureValue> values = featureCreationDto.getValues().stream().map(f -> new FeatureValue(f, feature)).collect(Collectors.toList());
        featureValueRepository.save(values);
        return values;
    }

    private List<FeatureValue> updateFeatureValues(final FeatureUpdateDto featureUpdateDto, final Feature feature) {
        final List<FeatureValue> valuesToUpdate = new ArrayList<>();
        final List<FeatureValueDto> featuresToUpdate = featureUpdateDto.getFeatureValueDtos().stream().filter(f -> f.getId() != null).collect(Collectors.toList());
        for(FeatureValueDto featureValueDto : featuresToUpdate) {
            final FeatureValue featureValue = new FeatureValue(featureValueDto.getValue(), feature);
            featureValue.setId(featureValueDto.getId());
            valuesToUpdate.add(featureValue);
        }
        featureValueRepository.save(valuesToUpdate);
        final List<FeatureValue> valuesToInsert = featureUpdateDto.getFeatureValueDtos().stream().filter(f -> f.getId() == null).map(f -> new FeatureValue(f.getValue(), feature)).collect(Collectors.toList());
        featureValueRepository.save(valuesToInsert);
        final List<FeatureValue> values = new ArrayList<>();
        values.addAll(valuesToUpdate);
        values.addAll(valuesToInsert);
        return values;
    }
}
