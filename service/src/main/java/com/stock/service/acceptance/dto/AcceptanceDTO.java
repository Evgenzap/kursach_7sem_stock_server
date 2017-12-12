package com.stock.service.acceptance.dto;

import com.stock.service.core.AbstractDTO;

public class AcceptanceDTO extends AbstractDTO {

    private String acceptanceDate;
    private Long consumerId;
    private Long supplierId;
    private Long stockId;
    private Long statusId;

    public AcceptanceDTO() {
    }

    public AcceptanceDTO(Long id,
                         Long creatorId,
                         String acceptanceDate,
                         Long consumerId,
                         Long supplierId,
                         Long stockId,
                         Long statusId
    ) {
        super(id, creatorId);
        this.acceptanceDate = acceptanceDate;
        this.consumerId = consumerId;
        this.supplierId = supplierId;
        this.stockId = stockId;
        this.statusId = statusId;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
