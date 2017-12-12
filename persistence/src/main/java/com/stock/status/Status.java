package com.stock.status;

import com.stock.acceptance.Acceptance;
import com.stock.commodity.Commodity;
import com.stock.company.Company;
import com.stock.core.BaseEntity;
import com.stock.stock.Stock;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity(name = "status")
public class Status extends BaseEntity {
    private String status;

    public Status() {
    }

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Acceptance> acceptanceList = new ArrayList<>();

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commodity> commodityList = new ArrayList<>();

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Company> companyList = new ArrayList<>();

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stock> stockList = new ArrayList<>();

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Acceptance> getAcceptanceList() {
        return acceptanceList;
    }

    public void setAcceptanceList(List<Acceptance> acceptanceList) {
        this.acceptanceList = acceptanceList;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
}
