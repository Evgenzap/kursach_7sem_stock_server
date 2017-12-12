package com.stock.stock;

import com.stock.commodity.Commodity;
import com.stock.core.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "stock_commodity")
public class StockCommodity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private int count;

    public StockCommodity() {
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
