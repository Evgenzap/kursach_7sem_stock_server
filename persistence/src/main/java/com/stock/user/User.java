package com.stock.user;

import com.stock.acceptance.Acceptance;
import com.stock.commodity.Commodity;
import com.stock.company.Company;
import com.stock.core.BaseEntity;
import com.stock.shipment.Shipment;
import com.stock.stock.Stock;
import com.stock.user.role.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hoard_user")
public class User extends BaseEntity {
    private String username;
    private String name;
    private String password;
    private String email;
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stock> stockList = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shipment> shipmentList = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Company> companyList = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commodity> commodityList = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Acceptance> acceptanceList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_PERSON")
    private List<Role> roles = new ArrayList<>();

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    public User() {
    }

    public User(String username, String name, String password, String email) {
        this.username = username;
        this.name = name;
        setPassword(password);
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public void setPasswordWithoutEncode(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public List<Shipment> getShipmentList() {
        return shipmentList;
    }

    public void setShipmentList(List<Shipment> shipmentList) {
        this.shipmentList = shipmentList;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public List<Acceptance> getAcceptanceList() {
        return acceptanceList;
    }

    public void setAcceptanceList(List<Acceptance> acceptanceList) {
        this.acceptanceList = acceptanceList;
    }

    public void addRole(Role roleItem) {
        roles.add(roleItem);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
