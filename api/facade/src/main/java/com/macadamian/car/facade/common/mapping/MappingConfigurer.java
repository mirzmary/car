package com.macadamian.car.facade.common.mapping;

import ma.glasnost.orika.MapperFactory;

public interface MappingConfigurer {
    void configure(MapperFactory factory);
}