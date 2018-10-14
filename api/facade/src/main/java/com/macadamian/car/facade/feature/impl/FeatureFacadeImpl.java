package com.macadamian.car.facade.feature.impl;

import com.macadamian.car.api.model.common.IdResponseModel;
import com.macadamian.car.api.model.common.ResponseModel;
import com.macadamian.car.api.model.feature.FeatureCreationRequestModel;
import com.macadamian.car.api.model.feature.FeatureUpdateRequestModel;
import com.macadamian.car.facade.feature.FeatureFacade;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.service.feature.FeatureService;
import com.macadamian.car.service.feature.dto.FeatureCreationDto;
import com.macadamian.car.service.feature.dto.FeatureUpdateDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:12 PM
 */
@Component
public class FeatureFacadeImpl implements FeatureFacade {

    @Autowired
    private FeatureService featureService;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public ResponseModel<IdResponseModel> createFeature(final FeatureCreationRequestModel model) {
        Assert.notNull(model, "FeatureCreationRequestModel should not be null when creating a feature");
        final FeatureCreationDto dto = mapperFacade.map(model, FeatureCreationDto.class);
        final Feature feature = featureService.create(dto);
        return new ResponseModel<>(new IdResponseModel(feature.getId()));
    }

    @Override
    public ResponseModel<IdResponseModel> updateFeature(final FeatureUpdateRequestModel model) {
        Assert.notNull(model, "FeatureUpdateRequestModel should not be null when updating a feature");
        final FeatureUpdateDto dto = mapperFacade.map(model, FeatureUpdateDto.class);
        final Feature feature = featureService.update(dto);
        return new ResponseModel<>(new IdResponseModel(feature.getId()));
    }
}
