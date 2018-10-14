package com.macadamian.car.service.car.dto;

import com.macadamian.car.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:52 PM
 */
public class CarFeatureDto extends BaseDTO {

    private static final long serialVersionUID = -4453202996392665840L;

    private Long featureId;

    private String name;

    private Long valueId;

    private String value;

    public CarFeatureDto(final Long featureId, final String name, final Long valueId, final String value) {
        this.featureId = featureId;
        this.name = name;
        this.valueId = valueId;
        this.value = value;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(final Long featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(final Long valueId) {
        this.valueId = valueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
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
        CarFeatureDto rhs = (CarFeatureDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.featureId, rhs.featureId)
                .append(this.name, rhs.name)
                .append(this.valueId, rhs.valueId)
                .append(this.value, rhs.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(featureId)
                .append(name)
                .append(valueId)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("featureId", featureId)
                .append("name", name)
                .append("valueId", valueId)
                .append("value", value)
                .toString();
    }
}
