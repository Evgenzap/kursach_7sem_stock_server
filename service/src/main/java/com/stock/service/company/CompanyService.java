package com.stock.service.company;

import com.stock.service.company.dto.CompanyDTO;
import com.stock.service.core.CustomMonoRequest;
import com.stock.service.core.CustomSoloRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CompanyService {
    ResponseEntity<?> createCompany(CompanyDTO company, HttpServletRequest request);
    ResponseEntity<?> deleteCompany(CustomSoloRequest company);
    ResponseEntity<?> deleteCompanyList(CustomMonoRequest companyList);
    ResponseEntity<?> getAllCompanyList(HttpServletRequest request);
}
