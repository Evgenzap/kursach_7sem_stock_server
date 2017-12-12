package com.stock.service.company.dto;

import com.stock.service.core.AbstractDTO;

public class CompanyDTO extends AbstractDTO {

    private Long statusId;
    private Long contractorId;

    private String companyName;
    private String telephone;
    private String email;
    private String boss;
    private String requisiteName;
    private String taxpayerNumber;

    public CompanyDTO() {}

    public CompanyDTO(Long id,
                      Long creatorId,
                      Long statusId,
                      Long contractorId,
                      String companyName,
                      String telephone,
                      String email,
                      String boss,
                      String requisiteName,
                      String taxpayerNumber) {
        super(id, creatorId);
        this.statusId = statusId;
        this.contractorId = contractorId;
        this.companyName = companyName;
        this.telephone = telephone;
        this.email = email;
        this.boss = boss;
        this.requisiteName = requisiteName;
        this.taxpayerNumber = taxpayerNumber;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getContractorId() {
        return contractorId;
    }

    public void setContractorId(Long contractorId) {
        this.contractorId = contractorId;
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
}
