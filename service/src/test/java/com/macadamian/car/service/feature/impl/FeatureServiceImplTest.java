package com.macadamian.car.service.feature.impl;

import com.macadamian.car.persistence.feature.FeatureRepository;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.featurevalue.FeatureValueRepository;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import com.macadamian.car.service.common.AbstractServiceImplTest;
import com.macadamian.car.service.common.dto.ValidationResult;
import com.macadamian.car.service.common.enums.Error;
import com.macadamian.car.service.common.exception.ValidationFailedServiceRuntimeException;
import com.macadamian.car.service.common.validationcomponent.CommonValidationComponent;
import com.macadamian.car.service.feature.FeatureService;
import com.macadamian.car.service.feature.dto.FeatureCreationDto;
import com.macadamian.car.service.feature.dto.FeatureUpdateDto;
import com.macadamian.car.service.feature.dto.FeatureValueDto;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.easymock.EasyMock.*;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 4:08 PM
 */
public class FeatureServiceImplTest extends AbstractServiceImplTest {

    @TestSubject
    private FeatureService featureService = new FeatureServiceImpl();

    @Mock
    private FeatureRepository featureRepository;

    @Mock
    private CommonValidationComponent commonValidationComponent;

    @Mock
    private FeatureValueRepository featureValueRepository;

    // region get Main features

    @Test
    public void testGetMainFeaturesEmptyList() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations
        expect(featureRepository.findByMain(true)).andReturn(new ArrayList<>());
        // Replay
        replayAll();
        final List<Feature> mainFeatureList = featureService.getMainFeatureList();
        // Run test scenario
        Assert.assertTrue(mainFeatureList.isEmpty());
        // Verify
        verifyAll();
    }

    @Test
    public void testGetMainFeatures() throws Exception {
        // Test data
        final List<Feature> features = getHelper().createMainFeatureList();
        // Reset
        resetAll();
        // Expectations
        expect(featureRepository.findByMain(true)).andReturn(features);
        // Replay
        replayAll();
        final List<Feature> mainFeatureList = featureService.getMainFeatureList();
        // Run test scenario
        Assert.assertEquals(features, mainFeatureList);
        // Verify
        verifyAll();
    }

    // endregion

    // region Validate Feature Creation Dto

    @Test(expected = IllegalArgumentException.class)
    public void testValidateFeatureCreationDtoWithNullDto() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        featureService.validateFeatureCreationDto(null);
        // Verify
        verifyAll();
    }

    @Test
    public void testValidateFeatureCreationDtoInvalidDto() throws Exception {
        // Test data
        final FeatureCreationDto dto = new FeatureCreationDto();
        // Reset
        resetAll();
        // Expectations
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(false);

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = featureService.validateFeatureCreationDto(dto);
        // Verify
        Assert.assertFalse(validationResult.isValid());
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_FEATURE_NAME));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_FEATURE_VALUES));
        verifyAll();
    }

    @Test
    public void testValidateFeatureCreationDtoInvalidFeatureList() throws Exception {
        // Test data
        final FeatureCreationDto dto = getHelper().createFeatureCreationDto();
        dto.getValues().add("");
        // Reset
        resetAll();
        // Expectations
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(true);
        for(String value : dto.getValues()) {
            expect(commonValidationComponent.validateString(value)).andReturn(false);
        }

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = featureService.validateFeatureCreationDto(dto);
        // Verify
        Assert.assertFalse(validationResult.isValid());
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_FEATURE_VALUES));
        verifyAll();
    }

    @Test
    public void testValidateFeatureCreationDtoValid() throws Exception {
        // Test data
        final FeatureCreationDto dto = getHelper().createFeatureCreationDto();
        // Reset
        resetAll();
        // Expectations
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(true);
        for(String value : dto.getValues()) {
            expect(commonValidationComponent.validateString(value)).andReturn(true);
        }

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = featureService.validateFeatureCreationDto(dto);
        // Verify
        Assert.assertTrue(validationResult.isValid());
        verifyAll();
    }

    // endregion

    // region Validate Feature Update Dto

    @Test(expected = IllegalArgumentException.class)
    public void testValidateFeatureUpdateDtoWithNullDto() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        featureService.validateFeatureUpdateDto(null);
        // Verify
        verifyAll();
    }

    @Test
    public void testValidateFeatureUpdateDtoInvalidDto() throws Exception {
        // Test data
        final FeatureUpdateDto dto = new FeatureUpdateDto();
        // Reset
        resetAll();
        // Expectations
        expect(commonValidationComponent.validateId(dto.getId())).andReturn(false);
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(false);

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = featureService.validateFeatureUpdateDto(dto);
        // Verify
        Assert.assertFalse(validationResult.isValid());
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_FEATURE_ID));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_FEATURE_NAME));
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_FEATURE_VALUES));
        verifyAll();
    }

    @Test
    public void testValidateFeatureUpdateDtoInvalidFeatureList() throws Exception {
        // Test data
        final FeatureUpdateDto dto = getHelper().createFeatureUpdateDto();
        dto.getFeatureValueDtos().add(new FeatureValueDto(""));
        // Reset
        resetAll();
        // Expectations
        expect(commonValidationComponent.validateId(dto.getId())).andReturn(true);
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(true);
        for(FeatureValueDto value : dto.getFeatureValueDtos()) {
            expect(commonValidationComponent.validateString(value.getValue())).andReturn(false);
        }

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = featureService.validateFeatureUpdateDto(dto);
        // Verify
        Assert.assertFalse(validationResult.isValid());
        Assert.assertTrue(validationResult.getErrors().contains(Error.INVALID_FEATURE_VALUES));
        verifyAll();
    }

    @Test
    public void testValidateFeatureUpdateDtoValid() throws Exception {
        // Test data
        final FeatureUpdateDto dto = getHelper().createFeatureUpdateDto();
        // Reset
        resetAll();
        // Expectations
        expect(commonValidationComponent.validateId(dto.getId())).andReturn(true);
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(true);
        for(FeatureValueDto value : dto.getFeatureValueDtos()) {
            expect(commonValidationComponent.validateString(value.getValue())).andReturn(true);
        }

        // Replay
        replayAll();

        // Run test scenario
        final ValidationResult validationResult = featureService.validateFeatureUpdateDto(dto);
        // Verify
        Assert.assertTrue(validationResult.isValid());
        verifyAll();
    }

    // endregion

    // region Create Feature

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFeatureWithNullDto() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        featureService.create(null);
        // Verify
        verifyAll();
    }

    @Test(expected = ValidationFailedServiceRuntimeException.class)
    public void testCreateFeatureWithInvalidDto() throws Exception {
        // Test data
        final FeatureCreationDto dto = new FeatureCreationDto();
        // Reset
        resetAll();
        // Expectations

        expect(commonValidationComponent.validateString(dto.getName())).andReturn(false);
        // Replay
        replayAll();

        // Run test scenario
        featureService.create(dto);
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateFeatureNormalFlow() throws Exception {
        // Test data
        final FeatureCreationDto dto = getHelper().createFeatureCreationDto();
        final Feature feature = new Feature(dto.getName(), dto.isMain());
        final List<FeatureValue> values = dto.getValues().stream().map(f -> new FeatureValue(f, feature)).collect(Collectors.toList());
        // Reset
        resetAll();
        // Expectations

        expect(commonValidationComponent.validateString(dto.getName())).andReturn(true);
        for(String value : dto.getValues()) {
            expect(commonValidationComponent.validateString(value)).andReturn(true);
        }
        expect(featureRepository.save(feature)).andReturn(feature);
        expect(featureValueRepository.save(values)).andReturn(values);
        // Replay
        replayAll();

        // Run test scenario
        final Feature result = featureService.create(dto);
        // Verify
        Assert.assertNotNull(feature);
        Assert.assertEquals(dto.getName(), result.getName());
        Assert.assertEquals(dto.isMain(), result.isMain());
        verifyAll();
    }

    // endregion

    // region Update Feature

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateFeatureWithNullDto() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        featureService.update(null);
        // Verify
        verifyAll();
    }

    @Test(expected = ValidationFailedServiceRuntimeException.class)
    public void testUpdateFeatureWithInvalidDto() throws Exception {
        // Test data
        final FeatureUpdateDto dto = new FeatureUpdateDto();
        // Reset
        resetAll();
        // Expectations

        expect(commonValidationComponent.validateId(dto.getId())).andReturn(false);
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(false);
        // Replay
        replayAll();

        // Run test scenario
        featureService.update(dto);
        // Verify
        verifyAll();
    }

    @Test
    public void testUpdateFeatureNormalFlow() throws Exception {
        // Test data
        final FeatureUpdateDto dto = getHelper().createFeatureUpdateDto();
        final Feature feature = new Feature(dto.getName(), dto.isMain());
        final List<FeatureValue> valuesForUpdate = new ArrayList<>();
        for(FeatureValueDto featureValueDto : dto.getFeatureValueDtos().stream().filter(f -> f.getId() != null).collect(Collectors.toList())) {
            final FeatureValue featureValue = new FeatureValue(featureValueDto.getValue(), feature);
            featureValue.setId(featureValueDto.getId());
            valuesForUpdate.add(featureValue);
        }
        final List<FeatureValue> valuesForInsert = dto.getFeatureValueDtos().stream().filter(f -> f.getId() == null).map(f -> new FeatureValue(f.getValue(), feature)).collect(Collectors.toList());
        // Reset
        resetAll();
        // Expectations
        expect(commonValidationComponent.validateId(dto.getId())).andReturn(true);
        expect(commonValidationComponent.validateString(dto.getName())).andReturn(true);
        for(FeatureValueDto value : dto.getFeatureValueDtos()) {
            expect(commonValidationComponent.validateString(value.getValue())).andReturn(true);
        }
        expect(featureRepository.findOne(dto.getId())).andReturn(feature);
        expect(featureRepository.save(feature)).andReturn(feature);
        expect(featureValueRepository.save(valuesForUpdate)).andReturn(valuesForUpdate);
        expect(featureValueRepository.save(valuesForInsert)).andReturn(valuesForInsert);
        // Replay
        replayAll();

        // Run test scenario
        final Feature result = featureService.update(dto);
        // Verify
        Assert.assertNotNull(feature);
        Assert.assertEquals(dto.getName(), result.getName());
        Assert.assertEquals(dto.isMain(), result.isMain());
        verifyAll();
    }

    // endregion
}
