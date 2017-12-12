package com.stock;

import com.stock.company.Company;
import com.stock.core.Attribute;
import com.stock.service.acceptance.AcceptanceRepository;
import com.stock.service.acceptanceCommodity.AcceptanceCommodityRepository;
import com.stock.service.attribute.AttributeRepository;
import com.stock.service.stockCommodity.StockCommodityRepository;
import com.stock.service.user.RoleRepository;
import com.stock.service.user.UserRepository;
import com.stock.status.Status;
import com.stock.service.commodity.CommodityRepository;
import com.stock.service.company.CompanyRepository;
import com.stock.stock.Stock;
import com.stock.user.User;
import com.stock.user.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.stock.service.shipment.ShipmentRepository;
import com.stock.service.shipmentCommodity.ShipmentCommodityRepository;
import com.stock.service.status.StatusRepository;
import com.stock.service.stock.StockRepository;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private final AcceptanceRepository acceptanceRepository;
    private final AcceptanceCommodityRepository acceptanceCommodityRepository;
    private final CommodityRepository commodityRepository;
    private final CompanyRepository companyRepository;
    private final ShipmentRepository shipmentRepository;
    private final ShipmentCommodityRepository shipmentCommodityRepository;
    private final StatusRepository statusRepository;
    private final StockRepository stockRepository;
    private final StockCommodityRepository stockCommodityRepository;
    private final UserRepository userRepository;
    private final AttributeRepository attributeRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DatabaseLoader(
            AcceptanceRepository acceptanceRepository,
            AcceptanceCommodityRepository acceptanceCommodityRepository,
            CommodityRepository commodityRepository,
            CompanyRepository companyRepository,
            ShipmentRepository shipmentRepository,
            ShipmentCommodityRepository shipmentCommodityRepository,
            StatusRepository statusRepository,
            StockRepository stockRepository,
            StockCommodityRepository stockCommodityRepository,
            UserRepository userRepository,
            AttributeRepository attributeRepository,
            RoleRepository roleRepository
    ) {
        this.acceptanceRepository = acceptanceRepository;
        this.acceptanceCommodityRepository = acceptanceCommodityRepository;
        this.commodityRepository = commodityRepository;
        this.companyRepository = companyRepository;
        this.shipmentRepository = shipmentRepository;
        this.shipmentCommodityRepository = shipmentCommodityRepository;
        this.statusRepository = statusRepository;
        this.stockRepository = stockRepository;
        this.stockCommodityRepository = stockCommodityRepository;
        this.userRepository = userRepository;
        this.attributeRepository = attributeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Status status = new Status("active");
        User creator = new User("nick","kek", "1234", "spam@shit.com");
        User user = new User("stranger","zhenyaZap", "letmein", "spam@shit.com");
        Attribute contractor = new Attribute("physical");
        statusRepository.save(status);
        attributeRepository.save(contractor);
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        creator.addRole(roleAdmin);
        user.addRole(roleUser);
        userRepository.save(creator);
        userRepository.save(user);

        Stock stock1 = new Stock("big", "1-284", 34.345, creator, status);
        Stock stock2 = new Stock("middle", "1-283", 20.1458, user, status);
        Stock stock3 = new Stock("little", "1-282", 4.987, creator, status);
        stockRepository.save(stock1);
        stockRepository.save(stock2);
        stockRepository.save(stock3);

        Company company1 = new Company("dvesta", "375296230749", "bylnov.nick@dfds.com", "zhenyaZap", creator, status, "3242344", "235532", contractor);
        Company company2 = new Company("apple", "375296230749", "bylnov.nick@dfds.com", "zhenyaZap", creator, status, "3242344", "235532", contractor);
        Company company3 = new Company("netcracker", "375296230749", "bylnov.nick@dfds.com", "zhenyaZap", creator, status, "3242344", "235532", contractor);
        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
    }
}
