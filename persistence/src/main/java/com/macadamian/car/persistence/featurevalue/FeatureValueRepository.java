package com.macadamian.car.persistence.featurevalue;

import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:04 AM
 */
@Repository
public interface FeatureValueRepository extends JpaRepository<FeatureValue, Long> {
}
