package com.macadamian.car.service.user;

import com.macadamian.car.persistence.user.entity.User;
import com.macadamian.car.service.common.AbstractServiceIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Mary Mirzoyan
 * Date: 10/15/18
 * Time: 1:06 AM
 */
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetById() throws Exception {
        // Given
        final User user = getHelper().createAndPersistUser();
        flushAndClear();
        // When
        final User resultUser = userService.getById(user.getId());
        // Then
        Assert.assertEquals(user, resultUser);
    }

    @Test
    public void testCheckIfUserExistsForLogin() throws Exception {
        // Given
        final User user = getHelper().createAndPersistUser();
        flushAndClear();
        // When
        final boolean loginExists = userService.checkIfUserExistsForLogin(user.getLogin());
        // Then
        Assert.assertTrue(loginExists);
    }
}
