package com.macadamian.car.service.user;

import com.macadamian.car.persistence.user.entity.User;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 7:39 PM
 */
public interface UserService {

    boolean checkIfUserExistsForLogin(final String login);

    User getById(final Long userId);

    User getByLogin(final String login);
}
