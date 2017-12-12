package com.stock.service.stock;

import com.stock.service.core.CustomMonoRequest;
import com.stock.service.core.CustomSoloRequest;
import com.stock.service.core.ResponseStatus;
import com.stock.service.status.StatusRepository;
import com.stock.service.stock.dto.StockDTO;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final UserService userService;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository,
                            UserRepository userRepository,
                            StatusRepository statusRepository,
                            UserService userService
    ) {
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> createStock(StockDTO stock, HttpServletRequest request) {
        if (!Validator.stockDTOValidator.test(stock)) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        userService.setCreatorToDTO(request, stock);
        if (stock.getCreatorId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no user with such id"),
                    HttpStatus.OK);
        }
        User creator = userRepository.findOne(stock.getCreatorId());
        if (creator == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no user with such id"),
                    HttpStatus.OK);
        }
        Status status = statusRepository.findOne(stock.getStatusId());
        if (status == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no status with such id"),
                    HttpStatus.OK);
        }
        Stock stockEntity = Converter.toStock(creator, status).apply(stock);
        stockEntity = stockRepository.save(stockEntity);
        if (stockEntity == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "can't save stock entity"),
                    HttpStatus.OK);
        }
        StockDTO storedStockDTO = Converter.toStockDTO().apply(stockEntity);
        return new ResponseEntity<>(storedStockDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteStock(CustomSoloRequest stock) {
        if (stock == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        if (stock.getId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no stock id"),
                    HttpStatus.OK);
        }
        stockRepository.delete(stock.getId());
        return new ResponseEntity<>(new ResponseStatus(true, "stock with id = " + stock.getId() + " was deleted"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteStockList(CustomMonoRequest stockList) {
        if (stockList == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        if (stockList.getIds() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no stock list id"),
                    HttpStatus.OK);
        }
        stockList.getIds().stream().filter(Objects::nonNull).forEach(stockRepository::delete);
        return new ResponseEntity<>(new ResponseStatus(true, "stock with ids = " + stockList.getIds() + " were deleted"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllStockList(HttpServletRequest request) {
        User creator = userService.getUserByToken(request);
        if (creator == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no creator id"),
                    HttpStatus.OK);
        }
        List<Stock> stockList = stockRepository.findAllByCreator(creator);
        List<StockDTO> stockDTOList = stockList.stream()
                .map(Converter.toStockDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(stockDTOList, HttpStatus.CREATED);
    }
}
