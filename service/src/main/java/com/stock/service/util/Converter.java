package com.stock.service.util;

import com.stock.acceptance.Acceptance;
import com.stock.acceptance.AcceptanceCommodity;
import com.stock.commodity.Commodity;
import com.stock.company.Company;
import com.stock.core.Attribute;
import com.stock.service.acceptance.dto.AcceptanceDTO;
import com.stock.service.acceptanceCommodity.dto.AcceptanceCommodityDTO;
import com.stock.service.commodity.dto.CommodityDTO;
import com.stock.service.company.dto.CompanyDTO;
import com.stock.service.stock.dto.StockDTO;
import com.stock.service.user.dto.UserDTO;
import com.stock.status.Status;
import com.stock.stock.Stock;
import com.stock.user.User;

import java.util.function.Function;

public class Converter {

    public static Function<StockDTO, Stock> toStock(User creator, Status status) {
        return stockDTO -> {
            Stock stock = new Stock();
            if (stockDTO.getId() != null) {
                stock.setId(stockDTO.getId());
            }
            stock.setStockName(stockDTO.getStockName());
            stock.setStockAddress(stockDTO.getStockAddress());
            stock.setStockVolume(stockDTO.getStockVolume() != null ? HoardUtils.roundDouble(stockDTO.getStockVolume(), 3) : null);
            stock.setStatus(status);
            stock.setCreator(creator);
            return stock;
        };
    }

    public static Function<Stock, StockDTO> toStockDTO() {
        return stock -> {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setId(stock.getId());
            stockDTO.setStockName(stock.getStockName());
            stockDTO.setStockAddress(stock.getStockAddress());
            stockDTO.setStockVolume(stock.getStockVolume() != null ? HoardUtils.roundDouble(stock.getStockVolume(), 3) : null);
            stockDTO.setStatusId(stock.getStatus().getId());
            stockDTO.setCreatorId(stock.getCreator().getId());
            return stockDTO;
        };
    }

    public static Function<CompanyDTO, Company> toCompany(User creator,
                                                          Status status,
                                                          Attribute contractor
    ) {
        return companyDTO -> {
            Company company = new Company();
            if (companyDTO.getId() != null) {
                company.setId(companyDTO.getId());
            }
            company.setCompanyName(companyDTO.getCompanyName());
            company.setTelephone(companyDTO.getTelephone());
            company.setEmail(companyDTO.getEmail());
            company.setBoss(companyDTO.getBoss());
            company.setCreator(creator);
            company.setStatus(status);
            company.setRequisiteName(companyDTO.getRequisiteName());
            company.setTaxpayerNumber(companyDTO.getTaxpayerNumber());
            company.setContractor(contractor);
            return company;
        };
    }

    public static Function<Company, CompanyDTO> toCompanyDTO() {
        return company -> {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setId(company.getId());
            companyDTO.setCompanyName(company.getCompanyName());
            companyDTO.setTelephone(company.getTelephone());
            companyDTO.setEmail(company.getEmail());
            companyDTO.setBoss(company.getBoss());
            companyDTO.setCreatorId(company.getCreator().getId());
            companyDTO.setStatusId(company.getStatus().getId());
            companyDTO.setRequisiteName(company.getRequisiteName());
            companyDTO.setTaxpayerNumber(company.getTaxpayerNumber());
            companyDTO.setContractorId(company.getContractor().getId());
            return companyDTO;
        };
    }

    public static Function<AcceptanceDTO, Acceptance> toAcceptance(
            Company consumer,
            Company supplier,
            Stock stock,
            Status status,
            User creator
    ) {
        return acceptanceDTO -> {
            Acceptance acceptance = new Acceptance();
            if (acceptanceDTO.getId() != null) {
                acceptance.setId(acceptanceDTO.getId());
            }
            acceptance.setAcceptanceDate(DateHelper.stringToDate(acceptanceDTO.getAcceptanceDate()));
            acceptance.setAcceptConsumer(consumer);
            acceptance.setAcceptSupplier(supplier);
            acceptance.setStock(stock);
            acceptance.setStatus(status);
            acceptance.setCreator(creator);
            return acceptance;
        };
    }

    public static Function<Acceptance, AcceptanceDTO> toAcceptanceDTO() {
        return acceptance -> {
            AcceptanceDTO acceptanceDTO = new AcceptanceDTO();
            acceptanceDTO.setId(acceptance.getId());
            acceptanceDTO.setAcceptanceDate(DateHelper.DateToString(acceptance.getAcceptanceDate()));
            acceptanceDTO.setConsumerId(acceptance.getAcceptConsumer().getId());
            acceptanceDTO.setSupplierId(acceptance.getAcceptSupplier().getId());
            acceptanceDTO.setStockId(acceptance.getStock().getId());
            acceptanceDTO.setStatusId(acceptance.getStatus().getId());
            acceptanceDTO.setCreatorId(acceptance.getCreator().getId());
            return acceptanceDTO;
        };
    }

    public static Function<UserDTO, User> toUser() {
        return userDTO -> {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPasswordWithoutEncode(userDTO.getPassword());
            return user;
        };
    }

    public static Function<User, UserDTO> toUserDTO() {
        return user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            return userDTO;
        };
    }

    public static Function<CommodityDTO, Commodity> toCommodity(User creator, Status status) {
        return commodityDTO -> {
            Commodity commodity = new Commodity();
            if (commodityDTO.getId() != null) {
                commodity.setId(commodityDTO.getId());
            }
            commodity.setCommodityName(commodityDTO.getCommodityName());
            commodity.setVendorCode(commodityDTO.getVendorCode());
            commodity.setWeight(commodityDTO.getWeight());
            commodity.setVolume(commodityDTO.getVolume());
            commodity.setValueAddedTax(commodityDTO.getValueAddedTax());
            commodity.setCommodityImagePath(commodityDTO.getCommodityImagePath());
            commodity.setDescription(commodityDTO.getDescription());
            commodity.setPurchasePrice(commodityDTO.getPurchasePrice());
            commodity.setSellingPrice(commodityDTO.getSellingPrice());
            commodity.setStatus(status);
            commodity.setCreator(creator);
            return commodity;
        };
    }

    public static Function<Commodity, CommodityDTO> toCommodityDTO(String route) {
        return commodity -> {
            CommodityDTO commodityDTO = new CommodityDTO();
            commodityDTO.setId(commodity.getId());
            commodityDTO.setCommodityName(commodity.getCommodityName());
            commodityDTO.setVendorCode(commodity.getVendorCode());
            commodityDTO.setWeight(commodity.getWeight());
            commodityDTO.setVolume(commodity.getVolume());
            commodityDTO.setValueAddedTax(commodity.getValueAddedTax());
            if (route != null) {
                commodityDTO.setCommodityImagePath(route + commodity.getCommodityImagePath());
            }
            commodityDTO.setDescription(commodity.getDescription());
            commodityDTO.setPurchasePrice(commodity.getPurchasePrice());
            commodityDTO.setSellingPrice(commodity.getSellingPrice());
            commodityDTO.setStatusId(commodity.getStatus().getId());
            commodityDTO.setCreatorId(commodity.getCreator().getId());
            return commodityDTO;
        };
    }

    public static Function<AcceptanceCommodity, AcceptanceCommodityDTO> toAcceptanceCommodityDTO() {
        return acceptanceCommodity -> {
            AcceptanceCommodityDTO acceptanceCommodityDTO = new AcceptanceCommodityDTO();
            if (acceptanceCommodity.getId() == null) {
                acceptanceCommodityDTO.setId(acceptanceCommodity.getId());
            }
            acceptanceCommodityDTO.setAcceptanceId(acceptanceCommodity.getTargetAcceptance().getId());
            acceptanceCommodityDTO.setCommodityId(acceptanceCommodity.getCommodity().getId());
            acceptanceCommodityDTO.setCount(acceptanceCommodity.getCount());
            acceptanceCommodityDTO.setDiscount(acceptanceCommodity.getDiscount());
            return acceptanceCommodityDTO;
        };
    }

    public static Function<AcceptanceCommodityDTO, AcceptanceCommodity> toAcceptanceCommodity(Commodity commodity, Acceptance acceptance) {
        return acceptanceCommodityDTO -> {
            AcceptanceCommodity acceptanceCommodity = new AcceptanceCommodity();
            if (acceptanceCommodityDTO.getId() == null) {
                acceptanceCommodity.setId(acceptanceCommodityDTO.getId());
            }
            acceptanceCommodity.setCommodity(commodity);
            acceptanceCommodity.setTargetAcceptance(acceptance);
            acceptanceCommodity.setCount(acceptanceCommodityDTO.getCount());
            acceptanceCommodity.setDiscount(acceptanceCommodityDTO.getDiscount());
            return acceptanceCommodity;
        };
    }
}
