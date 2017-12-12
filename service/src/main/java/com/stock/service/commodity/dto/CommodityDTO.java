package com.stock.service.commodity.dto;

import com.stock.service.core.AbstractDTO;

public class CommodityDTO extends AbstractDTO  {

    private Long statusId;
    private String commodityName;
    private String vendorCode;
    private Double weight;
    private Double volume;
    private Float  valueAddedTax;
    private String commodityImagePath;
    private String description;
    private Float purchasePrice;
    private Float sellingPrice;

    public CommodityDTO() {}

    public CommodityDTO(Long id,
                        Long creatorId,
                        Long statusId,
                        String commodityName,
                        String vendorCode,
                        Double weight,
                        Double volume,
                        Float valueAddedTax,
                        String commodityImagePath,
                        String description,
                        Float purchasePrice,
                        Float sellingPrice
    ) {
        super(id, creatorId);
        this.statusId = statusId;
        this.commodityName = commodityName;
        this.vendorCode = vendorCode;
        this.weight = weight;
        this.volume = volume;
        this.valueAddedTax = valueAddedTax;
        this.commodityImagePath = commodityImagePath;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Float getValueAddedTax() {
        return valueAddedTax;
    }

    public void setValueAddedTax(Float valueAddedTax) {
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

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
