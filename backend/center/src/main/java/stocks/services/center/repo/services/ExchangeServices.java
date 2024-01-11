package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocks.services.center.domain.Investors;
import stocks.services.center.domain.Stock;
import stocks.services.center.domain.StockExchange;
import stocks.services.center.repo.ExchangeRepo;
import stocks.services.center.repo.InvestorRepo;
import stocks.services.center.repo.StockRepo;

import java.util.*;

@Service
public class ExchangeServices {
    private final StockRepo stockRepo;
    private final InvestorRepo investorRepo;
    private final ExchangeRepo exchangeRepo;

    @Autowired
    public ExchangeServices(StockRepo stockRepo, InvestorRepo investorRepo, ExchangeRepo exchangeRepo) {
        this.stockRepo = stockRepo;
        this.investorRepo = investorRepo;
        this.exchangeRepo = exchangeRepo;
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

    public Stock getStock(String sym) {
        return stockRepo.findBySymbol(sym);
    }

    public void openBuy(long id, String symbol, long assets) {
        Investors user = investorRepo.findById(id).orElse(null);
        Stock stock = stockRepo.findBySymbol(symbol);
        float cost = stock.getBuy() * assets;
        float balance = user.getBalance();
        if (balance < cost) {
            throw new IllegalArgumentException("Insufficient funds to open buy position.");
        }
        float left = balance - cost;
        Date openTime = new Date();
        user.setBalance(left);
        StockExchange stockExchange = new StockExchange();
        stockExchange.setBuy(stock.getBuy());
        stockExchange.setSell(0);
        stockExchange.setAssets(assets);
        stockExchange.setTimestamp(openTime);
        stockExchange.setSymbol(symbol);
        stockExchange.setUserId(id);

        user.getStockExchanges().add(stockExchange);
        investorRepo.save(user);
    }

    public float closeBuy(long id, String symbol) {
        Investors user = investorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Investor not found"));

        Stock stock = stockRepo.findBySymbol(symbol);
        if (stock == null) {
            throw new IllegalArgumentException("Stock not found for symbol: " + symbol);
        }

        Optional<StockExchange> purchaseOptional = Optional.ofNullable(exchangeRepo.findByUserIdAndSymbol(id, symbol));

        if (purchaseOptional.isPresent()) {
            StockExchange purchase = purchaseOptional.get();

            // Calculate total and cost
            double total = stock.getBuy() * purchase.getAssets();
            double cost = purchase.getBuy() * purchase.getAssets();

            // Calculate profit
            double profit = total - cost;

            // Update user balance
            user.setBalance(user.getBalance() + (float) profit);

            // Remove the purchase from the list (if applicable)
            exchangeRepo.delete(purchase);

            // Save the changes
            investorRepo.save(user);

            return (float) profit;
        } else {
            throw new IllegalArgumentException("Buy position not found for symbol: " + symbol);
        }
    }

    public void openSell(long id, String symbol, long assets) {
        Investors user = investorRepo.findById(id).orElse(null);
        Stock stock = stockRepo.findBySymbol(symbol);
        float cost = stock.getSell() * assets;
        float balance = user.getBalance();
        if (balance < cost) {
            throw new IllegalArgumentException("Insufficient funds to open sell position");
        }
        float left = balance - cost;
        Date openTime = new Date();
        user.setBalance(left);
        StockExchange stockExchange = new StockExchange();
        stockExchange.setBuy(0);
        stockExchange.setSell(stock.getSell());
        stockExchange.setAssets(assets);
        stockExchange.setTimestamp(openTime);
        stockExchange.setSymbol(symbol);
        stockExchange.setUserId(id);

        user.setStockExchanges(stockExchange);
        investorRepo.save(user);
    }

    public float closeSell(long id, String symbol) {
        Investors user = investorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Investor not found"));

        Stock stock = stockRepo.findBySymbol(symbol);
        if (stock == null) {
            throw new IllegalArgumentException("Stock not found for symbol: " + symbol);
        }

        Optional<StockExchange> purchaseOptional = Optional.ofNullable(exchangeRepo.findByUserIdAndSymbol(id, symbol));

        if (purchaseOptional.isPresent()) {
            StockExchange purchase = purchaseOptional.get();

            // Calculate total and cost
            double total = stock.getSell() * purchase.getAssets();
            double cost = purchase.getSell() * purchase.getAssets();

            // Calculate profit
            double profit = total - cost;

            // Update user balance
            user.setBalance(user.getBalance() + (float) profit);

            // Remove the purchase from the list (if applicable)
            exchangeRepo.delete(purchase);

            // Save the changes
            investorRepo.save(user);

            return (float) profit;
        } else {
            throw new IllegalArgumentException("Sell position not found for symbol: " + symbol);
        }
    }

    public List<StockExchange> showOpenHistory(long id) {
        StockExchange singleResult = exchangeRepo.findByUserId(id);

        // Wrap the single result in a list
        List<StockExchange> history = Collections.singletonList(singleResult);

        return history;
    }

}
