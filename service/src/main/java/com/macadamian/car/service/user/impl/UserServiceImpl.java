package com.macadamian.car.service.user.impl;

import com.macadamian.car.persistence.user.UserRepository;
import com.macadamian.car.persistence.user.entity.User;
import com.macadamian.car.service.common.exception.LoggerAwareServiceRuntimeException;
import com.macadamian.car.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 7:39 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkIfUserExistsForLogin(final String login) {
        Assert.hasText(login, "Login should not be null when validating if a user with the same login exists");
        final User user = userRepository.findByLogin(login);
        return user != null;
    }

    @Override
    public User getById(final Long userId) {
        Assert.notNull(userId, "userId can not be null when retrieving user by userId");
        return Optional.ofNullable(userRepository.findOne(userId))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find a user by specific id %s", userId));
    }

    @Override
    public User getByLogin(final String login) {
        Assert.notNull(login, "User login should not be null when getting user by login");
        return Optional.ofNullable(userRepository.findByLogin(login))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find a user by specific login %s", login));
    }
}
