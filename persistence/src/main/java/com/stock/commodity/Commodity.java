package com.stock.commodity;

import com.stock.core.BaseEntity;
import com.stock.status.Status;
import com.stock.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commodity")
public class Commodity extends BaseEntity {
    private String commodityName;
    private String vendorCode;
    private double weight;
    private double volume;
    private float  valueAddedTax;
    private String commodityImagePath;

    private String description;
    private float purchasePrice;
    private float sellingPrice;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Status status;

    public Commodity() {

    }

    public Commodity(String commodityName,
                     String vendorCode,
                     double weight,
                     double volume,
                     float valueAddedTax,
                     String commodityImagePath,
                     String description,
                     float purchasePrice,
                     float sellingPrice,
                     User creator,
                     Status status
    ) {
        this.commodityName = commodityName;
        this.vendorCode = vendorCode;
        this.weight = weight;
        this.volume = volume;
        this.valueAddedTax = valueAddedTax;
        this.commodityImagePath = commodityImagePath;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.creator = creator;
        this.status = status;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public float getValueAddedTax() {
        return valueAddedTax;
    }

    public void setValueAddedTax(float valueAddedTax) {
        this.valueAddedTax = valueAddedTax;
    }

    public String getCommodityImagePath() {
        return commodityImagePath;
    }

    public void setCommodityImagePath(String commodityImagePath) {
        this.commodityImagePath = commodityImagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
