package com.macadamian.car.service.car.dto;

import com.macadamian.car.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:41 AM
 */
public class CarCreationDto extends BaseDTO {

    private static final long serialVersionUID = -4145058982986963387L;

    private Long brandId;

    private Long modelId;

    private Long colorId;

    private Integer year;

    private BigDecimal price;

    private List<FeatureDto> features;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(final Long brandId) {
        this.brandId = brandId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(final Long modelId) {
        this.modelId = modelId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(final Long colorId) {
        this.colorId = colorId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public List<FeatureDto> getFeatures() {
        return features;
    }

    public void setFeatures(final List<FeatureDto> features) {
        this.features = features;
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
        CarCreationDto rhs = (CarCreationDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.brandId, rhs.brandId)
                .append(this.modelId, rhs.modelId)
                .append(this.colorId, rhs.colorId)
                .append(this.year, rhs.year)
                .append(this.price, rhs.price)
                .append(this.features, rhs.features)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(brandId)
                .append(modelId)
                .append(colorId)
                .append(year)
                .append(price)
                .append(features)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("brandId", brandId)
                .append("modelId", modelId)
                .append("colorId", colorId)
                .append("year", year)
                .append("price", price)
                .append("features", features)
                .toString();
    }
}
