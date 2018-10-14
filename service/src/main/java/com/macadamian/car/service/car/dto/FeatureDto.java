package com.macadamian.car.service.car.dto;

import com.macadamian.car.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 7:18 PM
 */
public class FeatureDto extends BaseDTO {

    private static final long serialVersionUID = 3342137882471677956L;

    private Long featureId;

    private Long valueId;

    public FeatureDto() {
        // Default constructor
    }

    public FeatureDto(final Long featureId, final Long valueId) {
        this.featureId = featureId;
        this.valueId = valueId;
    }

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
        FeatureDto rhs = (FeatureDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.featureId, rhs.featureId)
                .append(this.valueId, rhs.valueId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(featureId)
                .append(valueId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("featureId", featureId)
                .append("valueId", valueId)
                .toString();
    }
}
