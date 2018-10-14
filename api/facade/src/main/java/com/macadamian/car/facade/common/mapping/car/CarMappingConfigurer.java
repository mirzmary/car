package com.macadamian.car.facade.common.mapping.car;

import com.macadamian.car.api.model.car.CarCreationRequestModel;
import com.macadamian.car.api.model.car.CarFeatureListingModel;
import com.macadamian.car.facade.common.mapping.MappingConfigurer;
import com.macadamian.car.service.car.dto.CarCreationDto;
import com.macadamian.car.service.car.dto.CarFeatureDto;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 12:02 AM
 */
@Component
public class CarMappingConfigurer implements MappingConfigurer {

    @Override
    public void configure(final MapperFactory factory) {
        factory.classMap(CarCreationRequestModel.class, CarCreationDto.class)
                .byDefault()
                .register();
        factory.classMap(CarFeatureDto.class, CarFeatureListingModel.class)
                .byDefault()
                .register();
    }
}
