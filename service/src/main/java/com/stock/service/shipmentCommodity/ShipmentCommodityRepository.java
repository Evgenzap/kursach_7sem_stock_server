package com.stock.service.shipmentCommodity;

import com.stock.shipment.ShipmentCommodity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentCommodityRepository
        extends PagingAndSortingRepository<ShipmentCommodity, Long> {
}
