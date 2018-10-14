package com.macadamian.car.facade.user.impl;

import com.macadamian.car.facade.user.UserFacade;
import com.macadamian.car.service.user.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 10:34 PM
 */
@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private MapperFacade mapperFacade;

}
