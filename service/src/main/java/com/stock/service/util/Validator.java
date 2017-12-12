package com.stock.service.util;

import com.stock.service.acceptance.dto.AcceptanceDTO;
import com.stock.service.commodity.dto.CommodityDTO;
import com.stock.service.company.dto.CompanyDTO;
import com.stock.service.stock.dto.StockDTO;
import com.stock.service.user.dto.UserDTO;

import java.util.function.Predicate;

public class Validator {
    public static Predicate<StockDTO> stockDTOValidator = (StockDTO stockDTO) -> {
        if (stockDTO == null) {
            return false;
        }
        if (!Validator.isValidString.test(stockDTO.getStockName())) {
            return false;
        }
        if (!Validator.isValidString.test(stockDTO.getStockAddress())) {
            return false;
        }
        if (stockDTO.getStockVolume() == null) {
            return false;
        }
        if (stockDTO.getStatusId() == null) {
            return false;
        }
        return true;
    };

    public static Predicate<StockDTO> stockFullDTOValidator = (StockDTO stockDTO) -> {
        if (!Validator.stockDTOValidator.test(stockDTO)) {
            return false;
        }
        if (stockDTO.getId() == null) {
            return false;
        }
        if (stockDTO.getCreatorId() == null) {
            return false;
        }
        return true;
    };

    public static Predicate<String> isValidString =
            (String s) -> s != null && s.length() < Constants.MAX_LENGTH;

    public static Predicate<CompanyDTO> companyDTOValidator = (CompanyDTO company) -> {
        if (company == null) {
            return false;
        }
        if (!Validator.isValidString.test(company.getCompanyName())) {
            return false;
        }
        if (!Validator.isValidString.test(company.getTelephone())) {
            return false;
        }
        if (!Validator.isValidString.test(company.getEmail())) {
            return false;
        }
        if (!Validator.isValidString.test(company.getBoss())) {
            return false;
        }
        if (company.getStatusId() == null) {
            return false;
        }
        if (!Validator.isValidString.test(company.getRequisiteName())) {
            return false;
        }
        if (!Validator.isValidString.test(company.getTaxpayerNumber())) {
            return false;
        }
        if (company.getContractorId() == null) {
            return false;
        }
        return true;
    };

    public Predicate<CompanyDTO> companyFullDTOValidator = (CompanyDTO companyDTO) -> {
        if (!Validator.companyDTOValidator.test(companyDTO)) {
            return false;
        }
        if (companyDTO.getId() == null) {
            return false;
        }
        if (companyDTO.getCreatorId() == null) {
            return false;
        }
        return true;
    };

    public static Predicate<AcceptanceDTO> acceptanceDTOValidator = (AcceptanceDTO acceptanceDTO) -> {
        if (acceptanceDTO.getAcceptanceDate() == null) {
            return false;
        }
        if (acceptanceDTO.getConsumerId() == null) {
            return false;
        }
        if (acceptanceDTO.getSupplierId() == null) {
            return false;
        }
        if (acceptanceDTO.getStockId() == null) {
            return false;
        }
        if (acceptanceDTO.getStatusId() == null) {
            return false;
        }

        return true;
    };

    public static Predicate<AcceptanceDTO> acceptanceFullDTOValidator = (AcceptanceDTO acceptanceDTO) -> {
        if (!Validator.acceptanceDTOValidator.test(acceptanceDTO)) {
            return false;
        }
        if (acceptanceDTO.getId() == null) {
            return false;
        }
        if (acceptanceDTO.getCreatorId() == null) {
            return false;
        }
        return true;
    };

    public static Predicate<UserDTO> userDTOValidator = (UserDTO user) -> {
        if (user == null) {
            return false;
        }
        if (!Validator.isValidString.test(user.getUsername())) {
            return false;
        }
        if (!Validator.isValidString.test(user.getName())) {
            return false;
        }
        if (user.getEmail() == null) {
            return false;
        }
        if (user.getPassword() == null) {
            return false;
        }
        return true;
    };


    public static Predicate<CommodityDTO> commodityDTOValidator = (CommodityDTO commodityDTO) -> {
        if (commodityDTO == null) {
            return false;
        }
        if (!Validator.isValidString.test(commodityDTO.getCommodityName())) {
            return false;
        }
        if (!Validator.isValidString.test(commodityDTO.getVendorCode())) {
            return false;
        }
        if (commodityDTO.getWeight() == null) {
            return false;
        }
        if (commodityDTO.getVolume() == null) {
            return false;
        }
        if (commodityDTO.getValueAddedTax() == null) {
            return false;
        }
        if (commodityDTO.getCommodityImagePath() == null) {
            return false;
        }
        if (commodityDTO.getDescription() == null) {
            return false;
        }
        if (commodityDTO.getPurchasePrice() == null) {
            return false;
        }
        if (commodityDTO.getSellingPrice() == null) {
            return false;
        }
        return true;
    };

    public static Predicate<CommodityDTO> commodityFullDTOValidator = (CommodityDTO commodityDTO) -> {
        if (!Validator.commodityDTOValidator.test(commodityDTO)) {
            return false;
        }
        if (commodityDTO.getId() == null) {
            return false;
        }
        if (commodityDTO.getCreatorId() == null) {
            return false;
        }
        return true;
    };
}
