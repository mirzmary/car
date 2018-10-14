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
public class FeatureUpdateRequestModel extends RequestModel {

    private static final long serialVersionUID = 3996466530592649436L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("main")
    private boolean main;

    @JsonProperty("values")
    private List<FeatureValueModel> values;

    public FeatureUpdateRequestModel() {
        // Default Constructor
    }

    public FeatureUpdateRequestModel(final Long id, final String name, final boolean main, final List<FeatureValueModel> values) {
        this.id = id;
        this.name = name;
        this.main = main;
        this.values = values;
    }

    public String getName() {

        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public List<FeatureValueModel> getValues() {
        return values;
    }

    public void setValues(final List<FeatureValueModel> values) {
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
        FeatureUpdateRequestModel rhs = (FeatureUpdateRequestModel) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.name, rhs.name)
                .append(this.main, rhs.main)
                .append(this.values, rhs.values)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(name)
                .append(main)
                .append(values)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("name", name)
                .append("main", main)
                .append("values", values)
                .toString();
    }
}
