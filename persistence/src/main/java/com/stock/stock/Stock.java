package com.stock.stock;

import com.stock.core.BaseEntity;
import com.stock.status.Status;
import com.stock.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
public class Stock extends BaseEntity {
    private String stockName;
    private String stockAddress;
    private Double stockVolume;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<StockCommodity> commodityList = new ArrayList<>();

    @ManyToOne
    private User creator;

    @ManyToOne
    private Status status;

    public Stock() {
    }

    public Stock(String stockName, String stockAddress, Double stockVolume, User creator, Status status) {
        this.stockName = stockName;
        this.stockAddress = stockAddress;
        this.stockVolume = stockVolume;
        this.creator = creator;
        this.status = status;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockAddress() {
        return stockAddress;
    }

    public void setStockAddress(String stockAddress) {
        this.stockAddress = stockAddress;
    }

    public Double getStockVolume() {
        return stockVolume;
    }

    public void setStockVolume(Double stockVolume) {
        this.stockVolume = stockVolume;
    }

    public List<StockCommodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<StockCommodity> commodityList) {
        this.commodityList = commodityList;
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
