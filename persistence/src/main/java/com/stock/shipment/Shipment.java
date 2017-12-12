package com.stock.shipment;

import com.stock.company.Company;
import com.stock.core.BaseEntity;
import com.stock.status.Status;
import com.stock.stock.Stock;
import com.stock.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shipment")
public class Shipment extends BaseEntity {

    private Date shipmentDate;

    @ManyToOne
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "ship_consumer_id")
    private Company shipmentConsumer;

    @ManyToOne
    @JoinColumn(name = "ship_supplier_id")
    private Company shipmentSupplier;

    @ManyToOne
    private Status status;

    @ManyToOne
    private User creator;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShipmentCommodity> commodityList = new ArrayList<>();

    public Shipment() {}

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Company getShipmentConsumer() {
        return shipmentConsumer;
    }

    public void setShipmentConsumer(Company shipmentConsumer) {
        this.shipmentConsumer = shipmentConsumer;
    }

    public Company getShipmentSupplier() {
        return shipmentSupplier;
    }

    public void setShipmentSupplier(Company shipmentSupplier) {
        this.shipmentSupplier = shipmentSupplier;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<ShipmentCommodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<ShipmentCommodity> commodityList) {
        this.commodityList = commodityList;
    }
}
