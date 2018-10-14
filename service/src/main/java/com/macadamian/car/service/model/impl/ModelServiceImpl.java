package com.macadamian.car.service.model.impl;

import com.macadamian.car.persistence.model.ModelRepository;
import com.macadamian.car.persistence.model.entity.Model;
import com.macadamian.car.service.common.exception.LoggerAwareServiceRuntimeException;
import com.macadamian.car.service.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 7:09 PM
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Model getById(final Long id) {
        return Optional.ofNullable(modelRepository.findOne(id))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find Model by id - %s", id));
    }
}
