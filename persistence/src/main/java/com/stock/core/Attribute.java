package com.stock.core;

import com.stock.company.Company;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attribute")
public class Attribute extends BaseEntity {
    private String attribute;

    public Attribute() {}

    public Attribute(String attribute) {
        this.attribute = attribute;
    }

    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Company> companyList = new ArrayList<>();

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }
}
