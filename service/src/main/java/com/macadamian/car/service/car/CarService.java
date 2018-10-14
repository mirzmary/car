package com.macadamian.car.service.car;

import com.macadamian.car.persistence.car.entity.Car;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.CarFilterDto;
import com.macadamian.car.service.car.dto.CarListingDto;
import com.macadamian.car.service.common.dto.ValidationResult;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:37 AM
 */
public interface CarService {

    Car create(final CarCreationDto carCreationDto);

    List<CarListingDto> getAllCars();
}
