package com.macadamian.car.service.brand.impl;

import com.macadamian.car.persistence.brand.BrandRepository;
import com.macadamian.car.persistence.brand.entity.Brand;
import com.macadamian.car.service.brand.BrandService;
import com.macadamian.car.service.common.exception.LoggerAwareServiceRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 7:09 PM
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand getById(final Long id) {
        return Optional.ofNullable(brandRepository.findOne(id))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find Brand by id - %s", id));
    }
}
