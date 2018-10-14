package com.macadamian.car.persistence.car;

import com.macadamian.car.persistence.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:01 AM
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}
