package com.macadamian.car.api.model.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 11:48 PM
 */
public class CarFeatureModel implements Serializable {

    private static final long serialVersionUID = 6581025685500494412L;

    @JsonProperty("featureId")
    private Long featureId;

    @JsonProperty("valueId")
    private Long valueId;

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(final Long featureId) {
        this.featureId = featureId;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(final Long valueId) {
        this.valueId = valueId;
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
        CarFeatureModel rhs = (CarFeatureModel) obj;
        return new EqualsBuilder()
                .append(this.featureId, rhs.featureId)
                .append(this.valueId, rhs.valueId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(featureId)
                .append(valueId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("featureId", featureId)
                .append("valueId", valueId)
                .toString();
    }
}
