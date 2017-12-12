package com.stock.shipment;

import com.stock.commodity.Commodity;
import com.stock.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shipment_commodity")
public class ShipmentCommodity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    private int count;

    private float discount;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
