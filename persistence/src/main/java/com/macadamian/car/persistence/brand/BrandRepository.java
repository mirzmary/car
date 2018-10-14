package com.macadamian.car.persistence.brand;

import com.macadamian.car.persistence.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:04 AM
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
