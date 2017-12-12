package com.stock.service.acceptance;

import com.stock.acceptance.Acceptance;
import com.stock.company.Company;
import com.stock.service.acceptance.dto.AcceptanceDTO;
import com.stock.service.company.CompanyRepository;
import com.stock.service.core.CustomMonoRequest;
import com.stock.service.core.CustomSoloRequest;
import com.stock.service.core.ResponseStatus;
import com.stock.service.status.StatusRepository;
import com.stock.service.stock.StockRepository;
import com.stock.service.user.UserRepository;
import com.stock.service.user.UserService;
import com.stock.service.util.Converter;
import com.stock.service.util.Validator;
import com.stock.status.Status;
import com.stock.stock.Stock;
import com.stock.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class AcceptanceServiceImpl implements AcceptanceService {

    private final AcceptanceRepository acceptanceRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;
    private final StockRepository stockRepository;
    private final UserService userService;

    @Autowired
    public AcceptanceServiceImpl(AcceptanceRepository acceptanceRepository,
                                 UserRepository userRepository,
                                 CompanyRepository companyRepository,
                                 StatusRepository statusRepository,
                                 StockRepository stockRepository,
                                 UserService userService
    ) {
        this.acceptanceRepository = acceptanceRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
        this.stockRepository = stockRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> createAcceptance(AcceptanceDTO acceptance, HttpServletRequest request) {
        if (!Validator.acceptanceDTOValidator.test(acceptance)) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: wrong input value"),
                    HttpStatus.OK);
        }
        userService.setCreatorToDTO(request, acceptance);
        User creator = userRepository.findOne(acceptance.getCreatorId());
        if (creator == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no user with such id"),
                    HttpStatus.OK);
        }
        Company consumer = companyRepository.findOne(acceptance.getConsumerId());
        if (consumer == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no consumer company with such id"),
                    HttpStatus.OK);
        }
        Company supplier = companyRepository.findOne(acceptance.getSupplierId());
        if (supplier == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no supplier-company with such id"),
                    HttpStatus.OK);
        }
        Stock stock = stockRepository.findOne(acceptance.getStockId());
        if (stock == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no stock with such id"),
                    HttpStatus.OK);
        }
        Status status = statusRepository.findOne(acceptance.getStatusId());
        if (status == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no status with such id"),
                    HttpStatus.OK);
        }
        Acceptance acceptanceEntity = Converter.toAcceptance(consumer,
                supplier, stock, status, creator).apply(acceptance);
        acceptanceEntity = acceptanceRepository.save(acceptanceEntity);
        if (acceptanceEntity == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "can't save acceptance entity"),
                    HttpStatus.OK);
        }
        AcceptanceDTO storedAcceptanceDTO = Converter.toAcceptanceDTO().apply(acceptanceEntity);
        return new ResponseEntity<>(storedAcceptanceDTO, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<?> deleteAcceptance(CustomSoloRequest acceptance) {
        if (acceptance == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        if (acceptance.getId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no acceptance id"),
                    HttpStatus.OK);
        }
        acceptanceRepository.delete(acceptance.getId());
        return new ResponseEntity<>(new ResponseStatus(true, "acceptance with id = "
                + acceptance.getId() + " was deleted"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAcceptanceList(CustomMonoRequest acceptanceList) {
        if (acceptanceList == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        if (acceptanceList.getIds() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no acceptance list id"),
                    HttpStatus.OK);
        }
        acceptanceList.getIds().stream().filter(Objects::nonNull).forEach(acceptanceRepository::delete);
        return new ResponseEntity<>(new ResponseStatus(true, "acceptance with ids = " + acceptanceList.getIds() + " were deleted"),
                HttpStatus.OK);
    }
}
