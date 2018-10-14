package com.macadamian.car.persistence.feature;

import com.macadamian.car.persistence.feature.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:04 AM
 */
@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    List<Feature> findByMain(final boolean main);
}
