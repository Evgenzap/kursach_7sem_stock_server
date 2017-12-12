package com.stock.service.status;

import com.stock.status.Status;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {
}
