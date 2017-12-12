package com.stock.acceptance;

import com.stock.service.acceptance.AcceptanceService;
import com.stock.service.acceptance.dto.AcceptanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AcceptanceController {

    private final AcceptanceService acceptanceService;

    @Autowired
    public AcceptanceController(AcceptanceService acceptanceService) {
        this.acceptanceService = acceptanceService;
    }

    @RequestMapping(value = "/hoard/acceptance/create", method = RequestMethod.POST)
    public ResponseEntity<?> createCommodity(@RequestBody AcceptanceDTO acceptance, HttpServletRequest request) {
        return acceptanceService.createAcceptance(acceptance, request);
    }
}
