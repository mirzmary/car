package com.macadamian.car.persistence.carfeature.entity;

import com.macadamian.car.persistence.car.entity.Car;
import com.macadamian.car.persistence.common.entity.AbstractDomainEntityModel;
import com.macadamian.car.persistence.feature.entity.Feature;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:44 AM
 */
@Entity
@Table(name = "car_feature")
public class CarFeature extends AbstractDomainEntityModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_value_id")
    private FeatureValue featureValue;

    public Car getCar() {
        return car;
    }

    public void setCar(final Car car) {
        this.car = car;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(final Feature feature) {
        this.feature = feature;
    }

    public FeatureValue getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(final FeatureValue featureValue) {
        this.featureValue = featureValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        CarFeature rhs = (CarFeature) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.car, rhs.car)
                .append(this.feature, rhs.feature)
                .append(this.featureValue, rhs.featureValue)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(car)
                .append(feature)
                .append(featureValue)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("car", car)
                .append("feature", feature)
                .append("featureValue", featureValue)
                .toString();
    }
}
