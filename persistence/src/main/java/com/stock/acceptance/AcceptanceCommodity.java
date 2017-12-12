package com.stock.acceptance;

import com.stock.commodity.Commodity;
import com.stock.core.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "acceptance_commodity")
public class AcceptanceCommodity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "acceptance_id")
    private Acceptance targetAcceptance;

    private int count;

    private float discount;

    public AcceptanceCommodity() {
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Acceptance getTargetAcceptance() {
        return targetAcceptance;
    }

    public void setTargetAcceptance(Acceptance targetAcceptance) {
        this.targetAcceptance = targetAcceptance;
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
