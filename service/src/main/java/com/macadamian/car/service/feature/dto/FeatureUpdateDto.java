package com.macadamian.car.service.feature.dto;

import com.macadamian.car.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 5:02 PM
 */
public class FeatureUpdateDto extends BaseDTO {

    private static final long serialVersionUID = 7396169437593054171L;

    private Long id;

    private String name;

    private boolean main;

    private List<FeatureValueDto> featureValueDtos;

    public FeatureUpdateDto() {
        // Default Constructor
        featureValueDtos = new ArrayList<>();
    }

    public FeatureUpdateDto(final Long id, final String name, final boolean main, final List<FeatureValueDto> featureValueDtos) {
        this.id = id;
        this.name = name;
        this.main = main;
        this.featureValueDtos = featureValueDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public List<FeatureValueDto> getFeatureValueDtos() {
        return featureValueDtos;
    }

    public void setFeatureValueDtos(final List<FeatureValueDto> featureValueDtos) {
        this.featureValueDtos = featureValueDtos;
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
        FeatureUpdateDto rhs = (FeatureUpdateDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.name, rhs.name)
                .append(this.main, rhs.main)
                .append(this.featureValueDtos, rhs.featureValueDtos)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(name)
                .append(main)
                .append(featureValueDtos)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("name", name)
                .append("main", main)
                .append("featureValueDtos", featureValueDtos)
                .toString();
    }
}
