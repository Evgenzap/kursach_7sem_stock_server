package com.stock.service.commodity;

import com.stock.commodity.Commodity;
import com.stock.service.commodity.dto.CommodityDTO;
import com.stock.service.core.CustomSoloRequest;
import com.stock.service.core.ResponseStatus;
import com.stock.service.status.StatusRepository;
import com.stock.service.user.UserRepository;
import com.stock.service.user.UserService;
import com.stock.service.util.Converter;
import com.stock.service.util.Validator;
import com.stock.status.Status;
import com.stock.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommodityServiceImpl implements CommodityService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final CommodityRepository commodityRepository;
    private final UserService userService;

    @Autowired
    public CommodityServiceImpl(UserRepository userRepository,
                                StatusRepository statusRepository,
                                CommodityRepository commodityRepository,
                                UserService userService
    ) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.commodityRepository = commodityRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public ResponseEntity<?> createCommodity(CommodityDTO commodityDTO, HttpServletRequest request) {
        if (!Validator.commodityDTOValidator.test(commodityDTO)) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: wrong input value"),
                    HttpStatus.OK);
        }
        userService.setCreatorToDTO(request, commodityDTO);
        if (commodityDTO.getCreatorId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no creator with such id"),
                    HttpStatus.OK);
        }
        User creator = userRepository.findOne(commodityDTO.getCreatorId());
        if (creator == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no creator with such id"),
                    HttpStatus.OK);
        }
        Status status = statusRepository.findOne(commodityDTO.getStatusId());
        if (status == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no creator with such id"),
                    HttpStatus.OK);
        }
        Commodity commodityEntity = Converter.toCommodity(creator, status).apply(commodityDTO);
        commodityEntity = commodityRepository.save(commodityEntity);
        if (commodityEntity == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "can't save commodity entity"),
                    HttpStatus.OK);
        }
        CommodityDTO storedCommodityDTO = Converter.toCommodityDTO("").apply(commodityEntity);
        return new ResponseEntity<>(storedCommodityDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteCommodity(CustomSoloRequest commodity) {
        if (commodity == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        if (commodity.getId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        commodityRepository.delete(commodity.getId());
        return new ResponseEntity<>(new ResponseStatus(true, "commodity with id = " + commodity.getId() + " was deleted"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllCommodityList(final String route, HttpServletRequest request) {
        User creator = userService.getUserByToken(request);
        if (creator == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no creator id"),
                    HttpStatus.OK);
        }
        List<Commodity> commodityList = commodityRepository.findAllByCreator(creator);
        List<CommodityDTO> commodityDTOList = commodityList.stream()
                .map(Converter.toCommodityDTO(route))
                .collect(Collectors.toList());
        return new ResponseEntity<>(commodityDTOList, HttpStatus.CREATED);
    }
}
