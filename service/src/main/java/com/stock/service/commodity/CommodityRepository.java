package com.stock.service.commodity;

import com.stock.commodity.Commodity;
import com.stock.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {
    List<Commodity> findAllByCreator(User creator);
}
