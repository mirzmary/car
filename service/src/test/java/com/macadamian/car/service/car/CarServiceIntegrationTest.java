package com.macadamian.car.service.car;

import com.macadamian.car.persistence.brand.entity.Brand;
import com.macadamian.car.persistence.car.CarRepository;
import com.macadamian.car.persistence.car.entity.Car;
import com.macadamian.car.persistence.color.entity.Color;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.persistence.model.entity.Model;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.CarListingDto;
import com.macadamian.car.service.car.dto.FeatureDto;
import com.macadamian.car.service.common.AbstractServiceIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:38 AM
 */
public class CarServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testCreateCar() throws Exception {
        // Given
        final Brand brand = getHelper().createAndPersistBrand();
        final Model model = getHelper().createAndPersistModel();
        final Color color = getHelper().createAndPersistColor();
        final Feature feature = getHelper().createAndPersistTransmissionFeature();
        final List<FeatureValue> featureValues = getHelper().createAndPersistFeatureValues(feature);
        final CarCreationDto carCreationDto = getHelper().createCarCreationDto();
        carCreationDto.setBrandId(brand.getId());
        carCreationDto.setModelId(model.getId());
        carCreationDto.setColorId(color.getId());
        carCreationDto.setFeatures(Collections.singletonList(new FeatureDto(feature.getId(), featureValues.get(0).getId())));

        // When
        final Car car = carService.create(carCreationDto);
        // Than
        Assert.assertNotNull(car);
        Assert.assertEquals(brand, car.getBrand());

    }

    @Test
    public void testGetAllCars() throws Exception {
        // Given
        final Brand brand = getHelper().createAndPersistBrand();
        final Model model = getHelper().createAndPersistModel();
        final Color color = getHelper().createAndPersistColor();
        final Feature feature = getHelper().createAndPersistTransmissionFeature();
        final List<FeatureValue> featureValues = getHelper().createAndPersistFeatureValues(feature);
        final CarCreationDto carCreationDto = getHelper().createCarCreationDto();
        carCreationDto.setBrandId(brand.getId());
        carCreationDto.setModelId(model.getId());
        carCreationDto.setColorId(color.getId());
        carCreationDto.setFeatures(Collections.singletonList(new FeatureDto(feature.getId(), featureValues.get(0).getId())));
        flushAndClear();
        final Car car = carService.create(carCreationDto);
        flushAndClear();
        // When
        final List<CarListingDto> allCars = carService.getAllCars();
        // Than
        Assert.assertEquals(allCars.size(), 1);
        Assert.assertTrue(allCars.get(0).getId().equals(car.getId()));
    }
}
