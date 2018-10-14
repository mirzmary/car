package com.macadamian.car.service.car.dto;

import com.macadamian.car.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:41 PM
 */
public class CarFilterDto extends BaseDTO {

    private static final long serialVersionUID = 8008293466317085700L;

    private Long brandId;

    private Long modelId;

    private Long colorId;

    private Integer fromYear;

    private Integer toYear;

    private BigDecimal fromPrice;

    private BigDecimal toPrice;

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

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(final Integer fromYear) {
        this.fromYear = fromYear;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(final Integer toYear) {
        this.toYear = toYear;
    }

    public BigDecimal getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(final BigDecimal fromPrice) {
        this.fromPrice = fromPrice;
    }

    public BigDecimal getToPrice() {
        return toPrice;
    }

    public void setToPrice(final BigDecimal toPrice) {
        this.toPrice = toPrice;
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
        CarFilterDto rhs = (CarFilterDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.brandId, rhs.brandId)
                .append(this.modelId, rhs.modelId)
                .append(this.colorId, rhs.colorId)
                .append(this.fromYear, rhs.fromYear)
                .append(this.toYear, rhs.toYear)
                .append(this.fromPrice, rhs.fromPrice)
                .append(this.toPrice, rhs.toPrice)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(brandId)
                .append(modelId)
                .append(colorId)
                .append(fromYear)
                .append(toYear)
                .append(fromPrice)
                .append(toPrice)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("brandId", brandId)
                .append("modelId", modelId)
                .append("colorId", colorId)
                .append("fromYear", fromYear)
                .append("toYear", toYear)
                .append("fromPrice", fromPrice)
                .append("toPrice", toPrice)
                .toString();
    }
}
