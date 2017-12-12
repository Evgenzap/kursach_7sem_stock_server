package com.stock.service.stockCommodity;

import com.stock.stock.StockCommodity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommodityRepository extends PagingAndSortingRepository<StockCommodity, Long> {
}
