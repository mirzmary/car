package com.macadamian.car.api.model.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.macadamian.car.api.model.common.RequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:04 PM
 */
public class FeatureCreationRequestModel extends RequestModel {

    private static final long serialVersionUID = 3996466530592649436L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("main")
    private boolean main;

    @JsonProperty("values")
    private List<String> values;

    public FeatureCreationRequestModel() {
        // Default Constructor
    }

    public FeatureCreationRequestModel(final String name, final boolean main, final List<String> values) {
        this.name = name;
        this.main = main;
        this.values = values;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(final boolean main) {
        this.main = main;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(final List<String> values) {
        this.values = values;
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
        FeatureCreationRequestModel rhs = (FeatureCreationRequestModel) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.name, rhs.name)
                .append(this.main, rhs.main)
                .append(this.values, rhs.values)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(name)
                .append(main)
                .append(values)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .append("main", main)
                .append("values", values)
                .toString();
    }
}
