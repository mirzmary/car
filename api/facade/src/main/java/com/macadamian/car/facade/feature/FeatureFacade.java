package com.macadamian.car.facade.feature;

import com.macadamian.car.api.model.common.IdResponseModel;
import com.macadamian.car.api.model.common.ResponseModel;
import com.macadamian.car.api.model.feature.FeatureCreationRequestModel;
import com.macadamian.car.api.model.feature.FeatureUpdateRequestModel;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:10 PM
 */
public interface FeatureFacade {

    ResponseModel<IdResponseModel> createFeature(final FeatureCreationRequestModel model);

    ResponseModel<IdResponseModel> updateFeature(final FeatureUpdateRequestModel model);
}
