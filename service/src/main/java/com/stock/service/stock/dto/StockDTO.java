package com.stock.service.stock.dto;

import com.stock.service.core.AbstractDTO;
import com.stock.stock.Stock;
import com.stock.user.User;

public class StockDTO extends AbstractDTO {

    private String stockAddress;
    private String stockName;
    private Double stockVolume;
    private Long statusId;

    public StockDTO() {
    }

    public StockDTO(Long id,
                    Long creatorId,
                    Long statusId,
                    String stockAddress,
                    String stockName,
                    Double stockVolume
    ) {
        super(id, creatorId);
        this.statusId = statusId;
        this.stockAddress = stockAddress;
        this.stockName = stockName;
        this.stockVolume = stockVolume;
    }

    public String getStockAddress() {
        return stockAddress;
    }

    public void setStockAddress(String stockAddress) {
        this.stockAddress = stockAddress;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getStockVolume() {
        return stockVolume;
    }

    public void setStockVolume(Double stockVolume) {
        this.stockVolume = stockVolume;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
