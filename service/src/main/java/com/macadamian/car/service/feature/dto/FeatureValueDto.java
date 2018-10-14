package com.macadamian.car.service.feature.dto;

import com.macadamian.car.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 7:12 PM
 */
public class FeatureValueDto extends BaseDTO {

    private static final long serialVersionUID = -302372444439454067L;

    private Long id;

    private String value;

    public FeatureValueDto() {
        // Default Constructor
    }

    public FeatureValueDto(final String value) {
        this.value = value;
    }

    public FeatureValueDto(final Long id, final String value) {

        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
        FeatureValueDto rhs = (FeatureValueDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.value, rhs.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("value", value)
                .toString();
    }
}
