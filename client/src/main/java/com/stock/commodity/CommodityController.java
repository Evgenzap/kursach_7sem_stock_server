package com.stock.commodity;

import com.stock.service.commodity.CommodityService;
import com.stock.service.commodity.dto.CommodityDTO;
import com.stock.service.company.dto.CompanyDTO;
import com.stock.service.core.CustomSoloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommodityController {

    private final CommodityService commodityService;

    @Autowired
    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @RequestMapping(value = "/hoard/commodity/create", method = RequestMethod.POST)
    public ResponseEntity<?> createCommodity(@RequestBody CommodityDTO companyDTO, HttpServletRequest request) {
        return commodityService.createCommodity(companyDTO, request);
    }

    @RequestMapping(value = "/hoard/commodity/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStock(@RequestBody CustomSoloRequest company) {
        return commodityService.deleteCommodity(company);
    }

    @RequestMapping(value = "/hoard/commodity/all", method = RequestMethod.GET)
    ResponseEntity<?> getAllCommodityList(@RequestParam(name = "route") String route, HttpServletRequest request) {
        System.out.println(route);
        return commodityService.getAllCommodityList(route, request);
    }
}
