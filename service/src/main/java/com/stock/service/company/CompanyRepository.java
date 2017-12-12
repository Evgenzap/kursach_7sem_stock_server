package com.stock.service.company;

import com.stock.company.Company;
import com.stock.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
    List<Company> findAllByCreator(User creator);
}
