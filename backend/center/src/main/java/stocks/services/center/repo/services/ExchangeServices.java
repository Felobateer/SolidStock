package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocks.services.center.repo.StockRepo;

@Service
public class ExchangeServices {
    private final StockRepo stockRepo;
    private final InvestorRepo investorRepo;

    @Autowired
    public ExchangeServices(StockRepo stockRepo, InvestorRepo investorRepo) {
        this.stockRepo = stockRepo;
        this.investorRepo = investorRepo;
    }

    public
}
