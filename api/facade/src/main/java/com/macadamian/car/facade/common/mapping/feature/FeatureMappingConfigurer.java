package com.macadamian.car.facade.common.mapping.feature;

import com.macadamian.car.api.model.car.CarCreationRequestModel;
import com.macadamian.car.api.model.feature.FeatureUpdateRequestModel;
import com.macadamian.car.facade.common.mapping.MappingConfigurer;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.feature.dto.FeatureUpdateDto;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:27 PM
 */
@Component
public class FeatureMappingConfigurer implements MappingConfigurer {

    @Override
    public void configure(final MapperFactory factory) {
        factory.classMap(FeatureUpdateRequestModel.class, FeatureUpdateDto.class)
                .field("values", "featureValueDtos")
                .byDefault()
                .register();
    }
}
