package com.stock.acceptance;

import com.stock.core.BaseEntity;
import com.stock.company.Company;
import com.stock.status.Status;
import com.stock.stock.Stock;
import com.stock.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "acceptance")
public class Acceptance extends BaseEntity {

    private Date acceptanceDate;

    @ManyToOne
    @JoinColumn(name = "consumer")
    private Company acceptConsumer;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Company acceptSupplier;

    @ManyToOne
    private Stock stock;

    @ManyToOne
    private Status status;

    @ManyToOne
    private User creator;

    @OneToMany(mappedBy = "targetAcceptance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AcceptanceCommodity> commodityList = new ArrayList<>();

    public Acceptance() {
    }

    public Company getAcceptConsumer() {
        return acceptConsumer;
    }

    public void setAcceptConsumer(Company acceptConsumer) {
        this.acceptConsumer = acceptConsumer;
    }

    public Company getAcceptSupplier() {
        return acceptSupplier;
    }

    public void setAcceptSupplier(Company acceptSupplier) {
        this.acceptSupplier = acceptSupplier;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<AcceptanceCommodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<AcceptanceCommodity> commodityList) {
        this.commodityList = commodityList;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
