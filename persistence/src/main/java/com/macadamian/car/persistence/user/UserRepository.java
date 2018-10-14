package com.macadamian.car.persistence.user;

import com.macadamian.car.persistence.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 7:03 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(final String login);
}
