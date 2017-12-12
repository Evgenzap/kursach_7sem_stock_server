package com.stock.service.acceptance;

import com.stock.acceptance.Acceptance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptanceRepository extends PagingAndSortingRepository<Acceptance, Long> {
}
