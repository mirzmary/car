package com.macadamian.car.persistence.carfeature;

import com.macadamian.car.persistence.carfeature.entity.CarFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:44 AM
 */
@Repository
public interface CarFeatureRepository extends JpaRepository<CarFeature, Long> {
}
