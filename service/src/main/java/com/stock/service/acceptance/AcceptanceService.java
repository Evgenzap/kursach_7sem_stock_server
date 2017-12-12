package com.stock.service.acceptance;

import com.stock.service.acceptance.dto.AcceptanceDTO;
import com.stock.service.core.CustomMonoRequest;
import com.stock.service.core.CustomSoloRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AcceptanceService {
    ResponseEntity<?> createAcceptance(AcceptanceDTO acceptance, HttpServletRequest request);
    ResponseEntity<?> deleteAcceptance(CustomSoloRequest acceptance);
    ResponseEntity<?> deleteAcceptanceList(CustomMonoRequest acceptance);
}
