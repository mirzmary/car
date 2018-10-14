package com.macadamian.car.service.feature;

import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.service.common.AbstractServiceIntegrationTest;
import com.macadamian.car.service.feature.dto.FeatureCreationDto;
import com.macadamian.car.service.feature.dto.FeatureUpdateDto;
import com.macadamian.car.service.feature.dto.FeatureValueDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 5:51 PM
 */
public class FeatureServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private FeatureService featureService;

    @Test
    public void testCreateFeature() throws Exception {
        // Given
        final FeatureCreationDto featureCreationDto = getHelper().createFeatureCreationDto();
        // When
        final Feature feature = featureService.create(featureCreationDto);
        // Than
        Assert.assertEquals(featureCreationDto.getName(), feature.getName());
        Assert.assertEquals(featureCreationDto.isMain(), feature.isMain());
        Assert.assertEquals(featureCreationDto.getValues().size(), feature.getFeatureValues().size());
    }

    @Test
    public void testUpdateFeature() throws Exception {
        // Given
        final FeatureCreationDto featureCreationDto = getHelper().createFeatureCreationDto();
        final Feature feature = featureService.create(featureCreationDto);
        flushAndClear();
        final FeatureUpdateDto featureUpdateDto = new FeatureUpdateDto();
        featureUpdateDto.setId(feature.getId());
        featureUpdateDto.setName("zzz");
        featureUpdateDto.setMain(featureCreationDto.isMain());
        final List<FeatureValueDto> values = feature.getFeatureValues().stream().map(f -> new FeatureValueDto(f.getFeature().getId(), f.getFeature().getName())).collect(Collectors.toList());
        values.add(new FeatureValueDto("New Value"));
        featureUpdateDto.setFeatureValueDtos(values);
        // When
        final Feature updatedFeature = featureService.update(featureUpdateDto);
        // Than
        Assert.assertEquals(featureUpdateDto.getName(), updatedFeature.getName());
        Assert.assertEquals(featureUpdateDto.isMain(), updatedFeature.isMain());
        Assert.assertEquals(featureUpdateDto.getFeatureValueDtos().size(), updatedFeature.getFeatureValues().size());

        Assert.assertNotNull(featureCreationDto.getName(), updatedFeature.getName());
        Assert.assertNotEquals(featureCreationDto.getValues().size(), updatedFeature.getFeatureValues().size());
    }

    @Test
    public void testUpdateFeatureReplaceExistingValue() throws Exception {
        // Given
        final FeatureCreationDto featureCreationDto = getHelper().createFeatureCreationDto();
        final Feature feature = featureService.create(featureCreationDto);
        flushAndClear();
        final FeatureUpdateDto featureUpdateDto = new FeatureUpdateDto();
        featureUpdateDto.setId(feature.getId());
        featureUpdateDto.setName(featureCreationDto.getName());
        featureUpdateDto.setMain(featureCreationDto.isMain());
        final List<FeatureValueDto> values = feature.getFeatureValues().stream().map(f -> new FeatureValueDto(f.getFeature().getId(), f.getFeature().getName())).collect(Collectors.toList());
        values.set(0, new FeatureValueDto(values.get(0).getId(), "New Value"));
        featureUpdateDto.setFeatureValueDtos(values);
        // When
        final Feature updatedFeature = featureService.update(featureUpdateDto);
        // Than
        Assert.assertEquals(featureUpdateDto.getName(), updatedFeature.getName());
        Assert.assertEquals(featureUpdateDto.isMain(), updatedFeature.isMain());
        Assert.assertEquals(featureUpdateDto.getFeatureValueDtos().size(), updatedFeature.getFeatureValues().size());

        Assert.assertNotEquals(featureCreationDto.getValues().get(0), updatedFeature.getFeatureValues().get(0));
    }
}
