package com.macadamian.car.persistence.featurevalue.entity;

import com.macadamian.car.persistence.common.entity.AbstractDomainEntityModel;
import com.macadamian.car.persistence.common.entity.AbstractNameDomainEntityModel;
import com.macadamian.car.persistence.feature.entity.Feature;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:04 AM
 */
@Entity
@Table(name = "feature_value")
public class FeatureValue extends AbstractDomainEntityModel {

    private static final long serialVersionUID = 5297958461013040384L;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    public FeatureValue() {
        // Default Constructor
    }

    public FeatureValue(final String value, final Feature feature) {
        this.value = value;
        this.feature = feature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(final Feature feature) {
        this.feature = feature;
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
        FeatureValue rhs = (FeatureValue) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.value, rhs.value)
                .append(getIdOrNull(this.feature), getIdOrNull(rhs.feature))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(value)
                .append(getIdOrNull(feature))
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("value", value)
                .append("feature", getIdOrNull(feature))
                .toString();
    }
}
