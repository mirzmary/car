package com.macadamian.car.service.user.impl;

import com.macadamian.car.persistence.user.UserRepository;
import com.macadamian.car.persistence.user.entity.User;
import com.macadamian.car.service.common.AbstractServiceImplTest;
import com.macadamian.car.service.common.exception.LoggerAwareServiceRuntimeException;
import com.macadamian.car.service.helper.CommonTestHelper;
import com.macadamian.car.service.user.UserService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;

import static org.easymock.EasyMock.expect;

/**
 * User: Mary Mirzoyan
 * Date: 10/15/18
 * Time: 1:09 AM
 */
public class UserServiceImplTest extends AbstractServiceImplTest {

    @TestSubject
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    // region get by id

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdWithIllegalArguments() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.getById(null);
        // Verify
        verifyAll();
    }

    @Test(expected = LoggerAwareServiceRuntimeException.class)
    public void testGetByIdWithNoBookFound() throws Exception {
        // Test data
        final Long userId = 1L;
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findOne(userId)).andReturn(null);
        // Replay
        replayAll();

        // Run test scenario
        userService.getById(userId);
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByIdNormalFlow() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findOne(userId)).andReturn(user);
        // Replay
        replayAll();

        // Run test scenario
        final User result = userService.getById(userId);
        // Verify
        Assert.assertEquals(user, result);
        verifyAll();
    }

    // endregion

    // region check if user exists for login

    @Test(expected = IllegalArgumentException.class)
    public void testCheckIfUserExistsForLoginWithIllegalArgument() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.checkIfUserExistsForLogin(null);
        // Verify
        verifyAll();
    }

    @Test
    public void testCheckIfUserExistsForLoginNotWhenExists() throws Exception {
        // Test data
        final String login = CommonTestHelper.USER_LOGIN;
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findByLogin(login)).andReturn(null);
        // Replay
        replayAll();

        // Run test scenario
        final boolean userExists = userService.checkIfUserExistsForLogin(login);
        // Verify
        Assert.assertFalse(userExists);
        verifyAll();
    }

    @Test
    public void testCheckIfUserExistsForLoginWhenExists() throws Exception {
        // Test data
        final String login = CommonTestHelper.USER_LOGIN;
        final User user = getHelper().createUser();
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findByLogin(login)).andReturn(user);
        // Replay
        replayAll();

        // Run test scenario
        final boolean userExists = userService.checkIfUserExistsForLogin(login);
        // Verify
        Assert.assertTrue(userExists);
        verifyAll();
    }

    // endregion
}
