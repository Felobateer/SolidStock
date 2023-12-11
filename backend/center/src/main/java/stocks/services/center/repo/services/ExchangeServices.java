package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocks.services.center.domain.Stock;
import stocks.services.center.repo.InvestorRepo;
import stocks.services.center.repo.StockRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeServices {
    private final StockRepo stockRepo;
    private final InvestorRepo investorRepo;

    @Autowired
    public ExchangeServices(StockRepo stockRepo, InvestorRepo investorRepo) {
        this.stockRepo = stockRepo;
        this.investorRepo = investorRepo;
    }

    public List<Stock> getAllStocks() {
       return stockRepo.findAll();
    }

    public List<Stock> getTenStocks() {
        List<Stock> tenStocks = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            Optional<Stock> stockOptional = stockRepo.findById(i);
            stockOptional.ifPresent(tenStocks::add);
        }
        return tenStocks;
    }


}
