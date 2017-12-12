package com.stock.service.shipment;

import com.stock.shipment.Shipment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends PagingAndSortingRepository<Shipment, Long> {
}
