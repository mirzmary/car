package com.macadamian.car.service.car.validationcomponent.impl;

import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.FeatureDto;
import com.macadamian.car.service.car.validationcomponent.CarValidationComponent;
import com.macadamian.car.service.common.AbstractServiceImplTest;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.common.enums.Error;
import com.macadamian.car.service.feature.FeatureService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.easymock.EasyMock.expect;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 6:34 PM
 */
public class CarValidationComponentImplTest extends AbstractServiceImplTest {

    @TestSubject
    private CarValidationComponent carValidationComponent = new CarValidationComponentImpl();

    @Mock
    private FeatureService featureService;

    @Test
    public void testCreateCarWithInvalidDto() throws Exception {
        // Test data
        final CarCreationDto carCreationDto = new CarCreationDto();
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = carValidationComponent.validateCarCreationDto(carCreationDto);
        // Verify
        Assert.assertFalse(validationResult.isValid());
        Assert.assertEquals(validationResult.getErrors().size(), 6);
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_CAR_BRAND));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_CAR_MODEL));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_CAR_COLOR));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_CAR_YEAR));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_CAR_PRICE));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_CAR_FEATURES));
        verifyAll();
    }

    @Test
    public void testCreateCarWithNotAllMainFeaturesInclusive() throws Exception {
        // Test data
        final CarCreationDto carCreationDto = getHelper().createCarCreationDto();
        final List<Feature> mainFeatureList = getHelper().createMainFeatureList();
        final List<Feature> features = getHelper().createFeatureList().stream().filter(f -> !f.isMain()).collect(Collectors.toList());
        carCreationDto.setFeatures(Collections.singletonList(new FeatureDto(features.get(0).getId(), 1L)));
        // Reset
        resetAll();
        // Expectations
        expect(featureService.getMainFeatureList()).andReturn(mainFeatureList);

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = carValidationComponent.validateCarCreationDto(carCreationDto);
        // Verify
        Assert.assertFalse(validationResult.isValid());
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_CAR_FEATURES));
        verifyAll();
    }

    @Test
    public void testCreateCarWithAllMainFeaturesInclusive() throws Exception {
        // Test data
        final CarCreationDto carCreationDto = getHelper().createCarCreationDto();
        final List<Feature> mainFeatureList = getHelper().createMainFeatureList();
        final List<FeatureDto> featureDtos = mainFeatureList.stream().map(f -> new FeatureDto(f.getId(), f.getId())).collect(Collectors.toList());
        carCreationDto.setFeatures(featureDtos);
        // Reset
        resetAll();
        // Expectations
        expect(featureService.getMainFeatureList()).andReturn(mainFeatureList);

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = carValidationComponent.validateCarCreationDto(carCreationDto);
        // Verify
        Assert.assertTrue(validationResult.isValid());
        verifyAll();
    }

}
