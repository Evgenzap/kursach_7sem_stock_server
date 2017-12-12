package com.stock.stock;

import com.stock.service.core.CustomMonoRequest;
import com.stock.service.core.CustomSoloRequest;
import com.stock.service.stock.StockService;
import com.stock.service.stock.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "/hoard/stock/create", method = RequestMethod.POST)
    public ResponseEntity<?> createStock(@RequestBody StockDTO stock, HttpServletRequest request) {
        return stockService.createStock(stock, request);
    }

    @RequestMapping(value = "/hoard/stock/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStock(@RequestBody CustomSoloRequest stock) {
        return stockService.deleteStock(stock);
    }

    @RequestMapping(value = "/hoard/stock/list/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStockList(@RequestBody CustomMonoRequest stockList) {
        return stockService.deleteStockList(stockList);
    }

    @RequestMapping(value = "/hoard/stock/all", method = RequestMethod.GET)
    ResponseEntity<?> getAllStockList(HttpServletRequest request) {
        return stockService.getAllStockList(request);
    }
}
