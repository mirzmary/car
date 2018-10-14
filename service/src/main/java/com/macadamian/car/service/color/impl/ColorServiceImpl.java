package com.macadamian.car.service.color.impl;

import com.macadamian.car.persistence.color.ColorRepository;
import com.macadamian.car.persistence.color.entity.Color;
import com.macadamian.car.service.color.ColorService;
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
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Color getById(final Long id) {
        return Optional.ofNullable(colorRepository.findOne(id))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find Color by id - %s", id));
    }
}
