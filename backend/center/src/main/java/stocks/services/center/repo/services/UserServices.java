package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocks.services.center.repo.InvestorRepo;

@Service
public class UserServices {
    private final InvestorRepo investorRepo;

    @Autowired
    public UserServices(InvestorRepo investorRepo) {
        this.investorRepo = investorRepo;
    }


}
