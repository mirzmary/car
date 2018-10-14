package com.macadamian.car.facade.car;

import com.macadamian.car.api.model.car.CarCreationRequestModel;
import com.macadamian.car.api.model.car.CarFeatureListingModel;
import com.macadamian.car.api.model.car.CarListingResponseModel;
import com.macadamian.car.api.model.common.IdResponseModel;
import com.macadamian.car.api.model.common.ResponseListModel;
import com.macadamian.car.api.model.common.ResponseModel;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 11:46 PM
 */
public interface CarFacade {

    ResponseModel<IdResponseModel> createCar(final CarCreationRequestModel carCreationRequestModel);

    ResponseListModel<CarListingResponseModel> getCarList();
}
