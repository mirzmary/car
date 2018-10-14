package com.macadamian.car.service.car.impl;

import com.macadamian.car.persistence.brand.entity.Brand;
import com.macadamian.car.persistence.car.CarRepository;
import com.macadamian.car.persistence.car.entity.Car;
import com.macadamian.car.persistence.carfeature.CarFeatureRepository;
import com.macadamian.car.persistence.color.entity.Color;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.model.entity.Model;
import com.macadamian.car.service.brand.BrandService;
import com.macadamian.car.service.car.CarService;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.CarListingDto;
import com.macadamian.car.service.car.dto.FeatureDto;
import com.macadamian.car.service.car.validationcomponent.CarValidationComponent;
import com.macadamian.car.service.color.ColorService;
import com.macadamian.car.service.common.AbstractServiceImplTest;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.common.enums.Error;
import com.macadamian.car.service.common.exception.ValidationFailedServiceRuntimeException;
import com.macadamian.car.service.common.validationcomponent.CommonValidationComponent;
import com.macadamian.car.service.feature.FeatureService;
import com.macadamian.car.service.featurevalue.FeatureValueService;
import com.macadamian.car.service.model.ModelService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.getCurrentArguments;
import static org.easymock.EasyMock.isA;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:39 AM
 */
public class CarServiceImplTest extends AbstractServiceImplTest {

    @TestSubject
    private CarService carService = new CarServiceImpl();

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarValidationComponent carValidationComponent;

    @Mock
    private FeatureService featureService;

    @Mock
    private BrandService brandService;

    @Mock
    private ModelService modelService;

    @Mock
    private ColorService colorService;

    @Mock
    private FeatureValueService featureValueService;

    @Mock
    private CarFeatureRepository carFeatureRepository;

    //  region Create Car

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCarWithNullDto    () throws Exception {
        // Test data

        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        carService.create(null);
        // Verify
        verifyAll();
    }

    @Test(expected = ValidationFailedServiceRuntimeException.class)
    public void testCreateCarWithInvalidDto    () throws Exception {
        // Test data
        final CarCreationDto carCreationDto = new CarCreationDto();
        final ValidationResult validationResult = new ValidationResult();
        validationResult.put(Error.INVALID_CAR_BRAND, "Car Brand is invalid");
        // Reset
        resetAll();
        // Expectations
        expect(carValidationComponent.validateCarCreationDto(carCreationDto)).andReturn(validationResult);
        // Replay
        replayAll();
        // Run test scenario

        carService.create(carCreationDto);
        replayAll();
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateCar() throws Exception {
        // Test data
        final CarCreationDto carCreationDto = getHelper().createCarCreationDto();
        final ValidationResult validationResult = new ValidationResult();
        final Brand brand = getHelper().createBrand();
        brand.setId(carCreationDto.getBrandId());
        final Model model = getHelper().createModel();
        model.setId(carCreationDto.getModelId());
        final Color color = getHelper().createColor();
        color.setId(carCreationDto.getColorId());
        // Reset
        resetAll();
        // Expectations
        expect(carValidationComponent.validateCarCreationDto(carCreationDto)).andReturn(new ValidationResult());
        expect(brandService.getById(carCreationDto.getBrandId())).andReturn(brand);
        expect(modelService.getById(carCreationDto.getModelId())).andReturn(model);
        expect(colorService.getById(carCreationDto.getColorId())).andReturn(color);
        for(FeatureDto featureDto : carCreationDto.getFeatures()) {
            final Feature feature = getHelper().createFeature();
            expect(featureService.getById(featureDto.getFeatureId())).andReturn(feature);
            expect(featureValueService.getById(featureDto.getValueId())).andReturn(getHelper().createFeatureValue(feature));
        }
        expect(carRepository.save(isA(Car.class))).andAnswer(() -> (Car)getCurrentArguments()[0]);
        expect(carFeatureRepository.save(isA(List.class))).andAnswer(() -> (List)getCurrentArguments()[0]);
        // Replay
        replayAll();
        // Run test scenario
        final Car car = carService.create(carCreationDto);
        // Verify
        Assert.assertNotNull(car);
        Assert.assertEquals(carCreationDto.getBrandId(), car.getBrand().getId());
        Assert.assertEquals(carCreationDto.getModelId(), car.getModel().getId());
        Assert.assertEquals(carCreationDto.getColorId(), car.getColor().getId());

        verifyAll();
    }

    // endregion

    // region Car listing

    @Test
    public void testGetAllCars() throws Exception {
        // Test data
        final List<Car> cars = Collections.singletonList(getHelper().createCar());
        // Reset
        resetAll();
        // Expectations
        expect(carRepository.findAll()).andReturn(cars);
        // Replay
        replayAll();

        // Run test scenario
        final List<CarListingDto> allCars = carService.getAllCars();
        // Verify
        Assert.assertEquals(allCars.size(), 1);
        Assert.assertEquals(cars.get(0).getPrice(), allCars.get(0).getPrice());
        verifyAll();
    }

    // endregion

}
