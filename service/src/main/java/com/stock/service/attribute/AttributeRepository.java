package com.stock.service.attribute;

import com.stock.core.Attribute;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {
}
