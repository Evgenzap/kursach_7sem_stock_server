package com.stock.service.acceptanceCommodity.dto;

import com.stock.service.core.AbstractDTO;

public class AcceptanceCommodityDTO extends AbstractDTO {
    private Long acceptanceId;
    private Long commodityId;
    private int count;
    private float discount;

    public AcceptanceCommodityDTO (
            Long id,
            Long creatorId,
            Long acceptanceId,
            Long commodityId,
            int count,
            float discount
    ) {
        super(id, creatorId);
        this.acceptanceId = acceptanceId;
        this.commodityId = commodityId;
        this.count = count;
        this.discount = discount;
    }

    public AcceptanceCommodityDTO() {}

    public Long getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(Long acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
