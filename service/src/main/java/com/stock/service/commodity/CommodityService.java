package com.stock.service.commodity;

import com.stock.service.commodity.dto.CommodityDTO;
import com.stock.service.core.CustomSoloRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CommodityService {
    ResponseEntity<?> createCommodity(CommodityDTO commodityDTO, HttpServletRequest request);
    ResponseEntity<?> deleteCommodity(CustomSoloRequest commodity);
    ResponseEntity<?> getAllCommodityList(String route, HttpServletRequest request);
}
