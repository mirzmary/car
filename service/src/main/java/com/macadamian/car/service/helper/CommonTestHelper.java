package com.macadamian.car.service.helper;


import com.macadamian.car.persistence.brand.entity.Brand;
import com.macadamian.car.persistence.car.entity.Car;
import com.macadamian.car.persistence.carfeature.entity.CarFeature;
import com.macadamian.car.persistence.color.entity.Color;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.persistence.model.entity.Model;
import com.macadamian.car.persistence.user.entity.User;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.FeatureDto;
import com.macadamian.car.service.feature.dto.FeatureCreationDto;
import com.macadamian.car.service.feature.dto.FeatureUpdateDto;
import com.macadamian.car.service.feature.dto.FeatureValueDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CommonTestHelper {

    public static final String USER_LOGIN = "test";
    public static final String USER_FIRST_NAME = "Amy";
    public static final String USER_LAST_NAME = "Green";
    public static final LocalDate USER_BIRTH_DATE = LocalDate.now().minusYears(24);

    private static final String brandName = "Toyota";
    private static final String modelName = "Camry";
    private static final String colorName = "yellow";
    private static final BigDecimal price = BigDecimal.valueOf(8000);
    private static final Integer year = 2011;

    public User createUser() {
        final User user = new User();
        user.setLogin(USER_LOGIN);
        user.setFirstName(USER_FIRST_NAME);
        user.setLastName(USER_LAST_NAME);
        user.setBirthDate(USER_BIRTH_DATE);

        return user;
    }



    public CarCreationDto createCarCreationDto() {
        final CarCreationDto carCreationDto = new CarCreationDto();
        carCreationDto.setBrandId(1L);
        carCreationDto.setModelId(2L);
        carCreationDto.setColorId(3L);
        carCreationDto.setPrice(price);
        carCreationDto.setYear(year);
        final List<FeatureDto> features = new ArrayList<>();
        features.add(new FeatureDto(4L, 5L));
        carCreationDto.setFeatures(features);
        return carCreationDto;
    }

    private Feature createTransmissionFeature() {
        final Feature feature = new Feature("Transmission", true);
        feature.setId(1L);
        return feature;
    }

    private Feature createFuelTypeFeature() {
        final Feature feature = new Feature("Fuel Type", true);
        feature.setId(2L);
        return feature;
    }

    private Feature createABSFeature() {
        final Feature feature = new Feature("ABS", false);
        feature.setId(3L);
        return feature;
    }

    private Feature createSRSFeature() {
        final Feature feature = new Feature("SRS", false);
        feature.setId(4L);
        return feature;
    }

    public List<Feature> createFeatureList() {
        final List<Feature> features = new ArrayList<>();
        features.add(createTransmissionFeature());
        features.add(createFuelTypeFeature());
        features.add(createABSFeature());
        features.add(createSRSFeature());
        return features;
    }

    public List<Feature> createMainFeatureList() {
        return createFeatureList().stream().filter(Feature::isMain).collect(Collectors.toList());
    }

    public Brand createBrand() {
        return new Brand("Toyota");
    }

    public Model createModel() {
        return new Model("Camry");
    }

    public Color createColor() {
        return new Color("yellow");
    }

    public Feature createFeature() {
        return createTransmissionFeature();
    }

    public FeatureValue createFeatureValue(final Feature feature) {
        return new FeatureValue("Auto", feature);
    }

    public List<FeatureValue> createFeatureValues(final Feature feature) {
        final List<FeatureValue> featureValues = new ArrayList<>();
        featureValues.add(new FeatureValue("Auto", feature));
        featureValues.add(new FeatureValue("Manual", feature));
        return featureValues;
    }

    public FeatureCreationDto createFeatureCreationDto() {
        final FeatureCreationDto dto = new FeatureCreationDto();
        dto.setName("Transmission");
        dto.getValues().add("Auto");
        dto.getValues().add("Manual");
        return dto;
    }

    public FeatureUpdateDto createFeatureUpdateDto() {
        final FeatureUpdateDto featureUpdateDto = new FeatureUpdateDto();
        featureUpdateDto.setId(1L);
        featureUpdateDto.setName("Transmission");
        featureUpdateDto.setMain(true);
        featureUpdateDto.getFeatureValueDtos().add(new FeatureValueDto(1L, "Auto"));
        featureUpdateDto.getFeatureValueDtos().add(new FeatureValueDto(2L, "Manual"));
        featureUpdateDto.getFeatureValueDtos().add(new FeatureValueDto("Robot"));
        return featureUpdateDto;
    }

    public Car createCar() {
        final Car car = new Car();
        car.setBrand(createBrand());
        car.setModel(createModel());
        car.setColor(createColor());
        car.setYear(2015);
        car.setPrice(BigDecimal.valueOf(8000));
        final CarFeature carFeature = new CarFeature();
        carFeature.setCar(car);
        carFeature.setFeature(createFeature());
        carFeature.setFeatureValue(createFeatureValue(carFeature.getFeature()));
        car.setCarFeatures(Collections.singletonList(carFeature));
        return car;
    }
}
