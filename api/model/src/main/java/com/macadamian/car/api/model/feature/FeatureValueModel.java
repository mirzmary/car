package com.macadamian.car.api.model.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:07 PM
 */
public class FeatureValueModel implements Serializable {

    private static final long serialVersionUID = -130959282437472485L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("value")
   private String value;

    public FeatureValueModel() {
        // Default Constructor
    }

    public FeatureValueModel(final Long id, final String value) {
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
        FeatureValueModel rhs = (FeatureValueModel) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.value, rhs.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("value", value)
                .toString();
    }
}
