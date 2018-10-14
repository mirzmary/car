package com.macadamian.car.api.model.car;

import com.macadamian.car.api.model.common.FacadeModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:50 PM
 */
public class CarListingResponseModel extends FacadeModel {

    private Long id;

    private Long brandId;

    private String brandName;

    private Long modelId;

    private String modelName;

    private Long colorId;

    private String colorName;

    private Integer year;

    private BigDecimal price;

    private List<CarFeatureListingModel> features;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(final Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(final String brandName) {
        this.brandName = brandName;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(final Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(final String modelName) {
        this.modelName = modelName;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(final Long colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(final String colorName) {
        this.colorName = colorName;
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

    public List<CarFeatureListingModel> getFeatures() {
        return features;
    }

    public void setFeatures(final List<CarFeatureListingModel> features) {
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
        CarListingResponseModel rhs = (CarListingResponseModel) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.brandId, rhs.brandId)
                .append(this.brandName, rhs.brandName)
                .append(this.modelId, rhs.modelId)
                .append(this.modelName, rhs.modelName)
                .append(this.colorId, rhs.colorId)
                .append(this.colorName, rhs.colorName)
                .append(this.year, rhs.year)
                .append(this.price, rhs.price)
                .append(this.features, rhs.features)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(brandId)
                .append(brandName)
                .append(modelId)
                .append(modelName)
                .append(colorId)
                .append(colorName)
                .append(year)
                .append(price)
                .append(features)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("brandId", brandId)
                .append("brandName", brandName)
                .append("modelId", modelId)
                .append("modelName", modelName)
                .append("colorId", colorId)
                .append("colorName", colorName)
                .append("year", year)
                .append("price", price)
                .append("features", features)
                .toString();
    }
}
