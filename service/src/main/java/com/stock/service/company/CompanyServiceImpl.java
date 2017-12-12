package com.stock.service.company;

import com.stock.company.Company;
import com.stock.core.Attribute;
import com.stock.service.attribute.AttributeRepository;
import com.stock.service.company.dto.CompanyDTO;
import com.stock.service.core.CustomMonoRequest;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final AttributeRepository attributeRepository;
    private final UserService userService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              UserRepository userRepository,
                              StatusRepository statusRepository,
                              AttributeRepository attributeRepository,
                              UserService userService
    ) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.attributeRepository = attributeRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> createCompany(CompanyDTO company, HttpServletRequest request) {
        if (!Validator.companyDTOValidator.test(company)) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: wrong input value"),
                    HttpStatus.OK);
        }
        userService.setCreatorToDTO(request, company);
        if (company.getCreatorId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no creator with such id"),
                    HttpStatus.OK);
        }
        User creator = userRepository.findOne(company.getCreatorId());
        if (creator == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no creator with such id"),
                    HttpStatus.OK);
        }
        Status status = statusRepository.findOne(company.getStatusId());
        if (status == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no status with such id"),
                    HttpStatus.OK);
        }
        Attribute contractor = attributeRepository.findOne(company.getContractorId());
        if (contractor == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no contractor with such id"),
                    HttpStatus.OK);
        }
        Company companyEntity = Converter.toCompany(creator, status, contractor).apply(company);
        companyEntity = companyRepository.save(companyEntity);
        if (companyEntity == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "can't save company entity"),
                    HttpStatus.OK);
        }
        CompanyDTO storedCompanyDTO = Converter.toCompanyDTO().apply(companyEntity);
        return new ResponseEntity<>(storedCompanyDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteCompany(CustomSoloRequest company) {
        if (company == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        if (company.getId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no company id"),
                    HttpStatus.OK);
        }
        companyRepository.delete(company.getId());
        return new ResponseEntity<>(new ResponseStatus(true, "company with id = " + company.getId() + " was deleted"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCompanyList(CustomMonoRequest companyList) {
        if (companyList == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.OK);
        }
        if (companyList.getIds() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no company list id"),
                    HttpStatus.OK);
        }
        companyList.getIds().stream().filter(Objects::nonNull).forEach(companyRepository::delete);
        return new ResponseEntity<>(new ResponseStatus(true, "company with ids = " + companyList.getIds() + " were deleted"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllCompanyList(HttpServletRequest request) {
        User creator = userService.getUserByToken(request);
        if (creator == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no creator id"),
                    HttpStatus.OK);
        }
        List<Company> companyList = companyRepository.findAllByCreator(creator);
        List<CompanyDTO> companyDTOList = companyList.stream()
                .map(Converter.toCompanyDTO())
                .collect(Collectors.toList());
        return new ResponseEntity<>(companyDTOList, HttpStatus.CREATED);
    }
}
