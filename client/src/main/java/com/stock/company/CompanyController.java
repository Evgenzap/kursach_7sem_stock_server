package com.stock.company;

import com.stock.service.company.CompanyService;
import com.stock.service.company.dto.CompanyDTO;
import com.stock.service.core.CustomMonoRequest;
import com.stock.service.core.CustomSoloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/hoard/company/create", method = RequestMethod.POST)
    public ResponseEntity<?> createCompany(@RequestBody CompanyDTO company, HttpServletRequest request) {
        return companyService.createCompany(company, request);
    }

    @RequestMapping(value = "/hoard/company/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStock(@RequestBody CustomSoloRequest company) {
        return companyService.deleteCompany(company);
    }

    @RequestMapping(value = "/hoard/company/list/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStockList(@RequestBody CustomMonoRequest companyList) {
        return companyService.deleteCompanyList(companyList);
    }

    @RequestMapping(value = "/hoard/company/all", method = RequestMethod.GET)
    ResponseEntity<?> getAllCompanyList(HttpServletRequest request) {
        return companyService.getAllCompanyList(request);
    }
}
