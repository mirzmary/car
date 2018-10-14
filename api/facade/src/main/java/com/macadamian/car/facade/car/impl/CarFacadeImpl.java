package com.macadamian.car.facade.car.impl;

import com.macadamian.car.api.model.car.CarCreationRequestModel;
import com.macadamian.car.api.model.car.CarListingResponseModel;
import com.macadamian.car.api.model.common.IdResponseModel;
import com.macadamian.car.api.model.common.ResponseListModel;
import com.macadamian.car.api.model.common.ResponseModel;
import com.macadamian.car.api.model.common.enums.ErrorEnum;
import com.macadamian.car.facade.car.CarFacade;
import com.macadamian.car.persistence.car.entity.Car;
import com.macadamian.car.service.car.CarService;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.CarListingDto;
import com.macadamian.car.service.car.validationcomponent.CarValidationComponent;
import com.macadamian.car.service.common.dto.ValidationResult;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 11:52 PM
 */
@Component
public class CarFacadeImpl implements CarFacade {

    @Autowired
    private CarService carService;

    @Autowired
    private CarValidationComponent carValidationComponent;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public ResponseModel<IdResponseModel> createCar(final CarCreationRequestModel carCreationRequestModel) {
        Assert.notNull(carCreationRequestModel, "carCreationRequestModel can not be null when creating a car");
        final ResponseModel<IdResponseModel> responseModel = new ResponseModel<>();
        final CarCreationDto carCreationDto = mapperFacade.map(carCreationRequestModel, CarCreationDto.class);
        final ValidationResult validationResult = carValidationComponent.validateCarCreationDto(carCreationDto);
        if (!validationResult.isValid()) {
            responseModel.setErrorList(mapperFacade.mapAsList(validationResult.getErrors(), ErrorEnum.class));
            return responseModel;
        }
        final Car car = carService.create(carCreationDto);
        responseModel.setResponse(new IdResponseModel(car.getId()));
        return responseModel;
    }

    @Override
    public ResponseListModel<CarListingResponseModel> getCarList() {
        final List<CarListingDto> cars = carService.getAllCars();
        final List<CarListingResponseModel> carListingResponseModels = mapperFacade.mapAsList(cars, CarListingResponseModel.class);
        return new ResponseListModel<>(carListingResponseModels);
    }
}
