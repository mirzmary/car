package com.macadamian.car.service.model;

import com.macadamian.car.persistence.model.entity.Model;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 7:09 PM
 */
public interface ModelService {

    Model getById(final Long id);
}
