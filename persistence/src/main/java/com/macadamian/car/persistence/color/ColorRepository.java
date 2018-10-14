package com.macadamian.car.persistence.color;

import com.macadamian.car.persistence.color.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:04 AM
 */
@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
