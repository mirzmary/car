package com.macadamian.car.persistence.model;

import com.macadamian.car.persistence.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:04 AM
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
