package com.stock.service.stock;

import com.stock.service.core.CustomMonoRequest;
import com.stock.service.core.CustomSoloRequest;
import com.stock.service.stock.dto.StockDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface StockService {
    ResponseEntity<?> createStock(StockDTO stock, HttpServletRequest request);
    ResponseEntity<?> deleteStock(CustomSoloRequest stock);
    ResponseEntity<?> deleteStockList(CustomMonoRequest stockList);
    ResponseEntity<?> getAllStockList(HttpServletRequest request);
}
