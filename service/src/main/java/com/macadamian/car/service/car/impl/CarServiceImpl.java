package com.macadamian.car.service.car.impl;

import com.macadamian.car.persistence.car.CarRepository;
import com.macadamian.car.persistence.car.entity.Car;
import com.macadamian.car.persistence.carfeature.CarFeatureRepository;
import com.macadamian.car.persistence.carfeature.entity.CarFeature;
import com.macadamian.car.service.brand.BrandService;
import com.macadamian.car.service.car.CarService;
import com.macadamian.car.service.car.dto.*;
import com.macadamian.car.service.car.validationcomponent.CarValidationComponent;
import com.macadamian.car.service.color.ColorService;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.common.exception.ValidationFailedServiceRuntimeException;
import com.macadamian.car.service.feature.FeatureService;
import com.macadamian.car.service.featurevalue.FeatureValueService;
import com.macadamian.car.service.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:37 AM
 */
@Service
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FeatureService featureService;

    @Autowired

    private CarValidationComponent carValidationComponent;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private FeatureValueService featureValueService;

    @Autowired
    private CarFeatureRepository carFeatureRepository;

    @Override
    @Transactional
    public Car create(final CarCreationDto carCreationDto) {
        Assert.notNull(carCreationDto, "carCreationDto can not be null when creating a car");
        final ValidationResult validationResult = carValidationComponent.validateCarCreationDto(carCreationDto);
        if (!validationResult.isValid()) {
            throw new ValidationFailedServiceRuntimeException(this, validationResult.getMessages().get(0));
        }

        final Car car = convertDtoToEntity(carCreationDto);
        carRepository.save(car);
        final List<CarFeature> carFeatures = createCarFeatures(carCreationDto, car);
        carFeatureRepository.save(carFeatures);
        return car;
    }

    @Override
    public List<CarListingDto> getAllCars() {
        final List<Car> cars = carRepository.findAll();
        final List<CarListingDto> carListingDtos = new ArrayList<>();
        for(Car car: cars) {
            final CarListingDto carListingDto = new CarListingDto();
            carListingDto.setId(car.getId());
            carListingDto.setBrandId(car.getBrand().getId());
            carListingDto.setBrandName(car.getBrand().getName());
            carListingDto.setModelId(car.getModel().getId());
            carListingDto.setModelName(car.getModel().getName());
            carListingDto.setColorId(car.getColor().getId());
            carListingDto.setColorName(car.getColor().getName());
            carListingDto.setYear(car.getYear());
            carListingDto.setPrice(car.getPrice());
            final List<CarFeatureDto> carFeatureDtos = car.getCarFeatures()
                    .stream()
                    .map(carFeature -> new CarFeatureDto(carFeature.getFeature().getId(), carFeature.getFeature().getName(), carFeature.getFeatureValue().getId(), carFeature.getFeatureValue().getValue()))
                    .collect(Collectors.toList());
            carListingDto.setFeatures(carFeatureDtos);
            carListingDtos.add(carListingDto);
        }
        return carListingDtos;
    }

    private Car convertDtoToEntity(final CarCreationDto carCreationDto) {
        final Car car = new Car();
        car.setBrand(brandService.getById(carCreationDto.getBrandId()));
        car.setModel(modelService.getById(carCreationDto.getModelId()));
        car.setColor(colorService.getById(carCreationDto.getColorId()));
        car.setYear(carCreationDto.getYear());
        car.setPrice(carCreationDto.getPrice());
        return car;
    }

    private List<CarFeature> createCarFeatures(final CarCreationDto carCreationDto, final Car car) {
        final List<CarFeature> carFeatures = new ArrayList<>();
        for (final FeatureDto feature : carCreationDto.getFeatures()) {
            final CarFeature carFeature = new CarFeature();
            carFeature.setFeature(featureService.getById(feature.getFeatureId()));
            carFeature.setFeatureValue(featureValueService.getById(feature.getValueId()));
            carFeature.setCar(car);
            carFeatures.add(carFeature);
        }
        return carFeatures;
    }
}
