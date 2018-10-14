package com.macadamian.car.persistence.car.entity;

import com.macadamian.car.persistence.brand.entity.Brand;
import com.macadamian.car.persistence.carfeature.entity.CarFeature;
import com.macadamian.car.persistence.color.entity.Color;
import com.macadamian.car.persistence.common.entity.AbstractDomainEntityModel;
import com.macadamian.car.persistence.model.entity.Model;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/13/18
 * Time: 12:02 AM
 */
@Entity
@Table(name = "car")
public class Car extends AbstractDomainEntityModel {

    private static final long serialVersionUID = -1922446771767747349L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "year")
    private Integer year;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<CarFeature> carFeatures;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(final Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(final Model model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
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

    public List<CarFeature> getCarFeatures() {
        return carFeatures;
    }

    public void setCarFeatures(final List<CarFeature> carFeatures) {
        this.carFeatures = carFeatures;
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
        Car rhs = (Car) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.brand, rhs.brand)
                .append(this.model, rhs.model)
                .append(this.color, rhs.color)
                .append(this.year, rhs.year)
                .append(this.price, rhs.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(brand)
                .append(model)
                .append(color)
                .append(year)
                .append(price)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("brand", brand)
                .append("model", model)
                .append("color", color)
                .append("year", year)
                .append("price", price)
                .toString();
    }
}
