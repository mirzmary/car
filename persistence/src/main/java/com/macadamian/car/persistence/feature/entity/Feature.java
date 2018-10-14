package com.macadamian.car.persistence.feature.entity;

import com.macadamian.car.persistence.common.entity.AbstractDomainEntityModel;
import com.macadamian.car.persistence.featurevalue.entity.FeatureValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:04 AM
 */
@Entity
@Table(name = "feature")
public class Feature extends AbstractDomainEntityModel {

    private static final long serialVersionUID = 1183211820288972357L;

    @Column(name = "main")
    private boolean main;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "feature", fetch = FetchType.LAZY)
    private List<FeatureValue> featureValues;

    public Feature() {
        // Default constructor
    }

    public Feature(final String name) {
        this.name = name;
        main = false;
    }

    public Feature(final String name, final boolean main) {
        this.name = name;
        this.main = main;
    }

    public List<FeatureValue> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(final List<FeatureValue> featureValues) {
        this.featureValues = featureValues;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(final boolean main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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
        Feature rhs = (Feature) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.main, rhs.main)
                .append(this.name, rhs.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(main)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("main", main)
                .append("name", name)
                .toString();
    }
}
