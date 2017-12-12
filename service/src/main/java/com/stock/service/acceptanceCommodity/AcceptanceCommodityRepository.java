package com.stock.service.acceptanceCommodity;

import com.stock.acceptance.AcceptanceCommodity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptanceCommodityRepository extends
        PagingAndSortingRepository<AcceptanceCommodity, Long> {
}
