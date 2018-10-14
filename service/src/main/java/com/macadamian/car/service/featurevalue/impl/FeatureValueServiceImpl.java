package com.macadamian.car.service.featurevalue.impl;

import com.macadamian.car.persistence.featurevalue.FeatureValueRepository;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.service.common.exception.LoggerAwareServiceRuntimeException;
import com.macadamian.car.service.featurevalue.FeatureValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 7:09 PM
 */
@Service
public class FeatureValueServiceImpl implements FeatureValueService {

    @Autowired
    private FeatureValueRepository featureValueRepository;

    @Override
    public FeatureValue getById(final Long id) {
        return Optional.ofNullable(featureValueRepository.findOne(id))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find feature value by id - %s", id));
    }
}
