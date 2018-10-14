package com.macadamian.car.service.brand;

import com.macadamian.car.persistence.brand.entity.Brand;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 7:09 PM
 */
public interface BrandService {

    Brand getById(final Long id);
}
