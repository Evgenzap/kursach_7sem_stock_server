package com.stock.company;

import com.stock.acceptance.Acceptance;
import com.stock.commodity.Commodity;
import com.stock.core.Attribute;
import com.stock.core.BaseEntity;
import com.stock.shipment.Shipment;
import com.stock.status.Status;
import com.stock.user.User;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    private String companyName;
    private String telephone;
    private String email;
    private String boss;

    @OneToMany(mappedBy = "acceptConsumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Acceptance> consumerList = new ArrayList<>();

    @OneToMany(mappedBy = "acceptSupplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Acceptance> supplierList = new ArrayList<>();

    @OneToMany(mappedBy = "shipmentConsumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shipment> shipConsumerList = new ArrayList<>();

    @OneToMany(mappedBy = "shipmentSupplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shipment> shipSupplierList = new ArrayList<>();

    @ManyToOne
    private User creator;

    @ManyToOne
    private Status status;

    private String requisiteName;
    private String taxpayerNumber;

    @ManyToOne
    private Attribute contractor;

    public Company() {

    }

    public Company(String companyName, String telephone, String email, String boss, User creator, Status status, String requisiteName, String taxpayerNumber, Attribute contractor) {
        this.companyName = companyName;
        this.telephone = telephone;
        this.email = email;
        this.boss = boss;
        this.creator = creator;
        this.status = status;
        this.requisiteName = requisiteName;
        this.taxpayerNumber = taxpayerNumber;
        this.contractor = contractor;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public List<Acceptance> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Acceptance> supplierList) {
        this.supplierList = supplierList;
    }

    public List<Acceptance> getConsumerList() {
        return consumerList;
    }

    public void setConsumerList(List<Acceptance> consumerList) {
        this.consumerList = consumerList;
    }

    public List<Shipment> getShipConsumerList() {
        return shipConsumerList;
    }

    public void setShipConsumerList(List<Shipment> shipConsumerList) {
        this.shipConsumerList = shipConsumerList;
    }

    public List<Shipment> getShipSupplierList() {
        return shipSupplierList;
    }

    public void setShipSupplierList(List<Shipment> shipSupplierList) {
        this.shipSupplierList = shipSupplierList;
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

    public String getRequisiteName() {
        return requisiteName;
    }

    public void setRequisiteName(String requisiteName) {
        this.requisiteName = requisiteName;
    }

    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    public Attribute getContractor() {
        return contractor;
    }

    public void setContractor(Attribute contractor) {
        this.contractor = contractor;
    }

}
