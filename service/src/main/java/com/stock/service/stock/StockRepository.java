package com.stock.service.stock;

import com.stock.stock.Stock;
import com.stock.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, Long> {
    List<Stock> findAllByCreator(User creator);
}
