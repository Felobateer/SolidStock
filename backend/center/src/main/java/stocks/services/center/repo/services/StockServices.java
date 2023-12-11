package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import stocks.services.center.domain.Stock;
import stocks.services.center.domain.StockPriceResponse;
import stocks.services.center.repo.StockRepo;

import java.util.List;


@Service
public class StockServices {
    private final StockRepo stockRepo;
    private final WebClient webClient;
    private final String apiInfoTemplate = "/api/v1/stock/profile2?symbol=%s&token=%s";
    private final String apiPriceTemplate = "/api/v1/quote?symbol=%s&token=%s";
    private final String apiKey = "cir4dhpr01qjff7d13ngcir4dhpr01qjff7d13o0";
    private final String[] apiStocks = {
            "AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "NVDA", "JPM", "V", "WMT", "JNJ",
            "PG", "UNH", "BAC", "HD", "INTC", "VZ", "DIS", "MA", "ADBE", "CMCSA", "KO",
            "NFLX", "PYPL", "PEP", "T", "NVAX", "MRNA", "ZM", "XOM", "CSCO", "CRM", "ABBV",
            "NKE", "IBM", "LMT", "COST", "ABT", "AMGN", "CVX", "PFE", "CVS", "GILD", "BMY",
            "MDT", "DHR", "UNP", "MMM", "SBUX", "AMT", "LRCX", "ADP", "INTU", "NOW", "GS",
            "MO", "SPG", "TGT"
    };

    @Autowired
    public StockServices(StockRepo stockRepo, WebClient.Builder builder) {
        this.stockRepo = stockRepo;
        this.webClient = builder.baseUrl("https://finnhub.io").build();
    }


    public void fetchData() {
        String apiKey = this.apiKey;


        for(String stock: this.apiStocks) {
            try {
                fetchStockInfo(stock, apiKey);
                fetchStockPrice(stock, apiKey);
            } catch (Exception e) {
                System.err.println("Error updating stock data for " +  stock + ": " + e.getMessage());
            }
        }
    }

    private void fetchStockInfo(String sym, String apiKey) {
        String apiInfoUrl = String.format(apiInfoTemplate, sym, apiKey);

        Mono<Stock> response = webClient.get()
                .uri(apiInfoUrl)
                .retrieve()
                .bodyToMono(Stock.class);

        response.subscribe(
                infoResponse -> {
                    Stock stockInfo = new Stock();
                    stockInfo.setName(infoResponse.getName());
                    stockInfo.setSymbol(sym);
                    stockInfo.setFinnhubIndustry(infoResponse.getFinnhubIndustry());
                    stockInfo.setWeburl(infoResponse.getWeburl());
                stockRepo.save(stockInfo);
                    }, error -> {
                    System.err.println("Error fetching stock info: " + error.getMessage());
                }
        );
    }

    private void fetchStockPrice(String symbol, String apiKey) {
        String apiPriceUrl = String.format(apiPriceTemplate, symbol, apiKey);

        Mono<StockPriceResponse> responseMono = webClient.get().uri(apiPriceUrl).retrieve().bodyToMono(StockPriceResponse.class);

        responseMono.subscribe(
                priceResponse -> {
                    // Update existing stock entity or save new entity with price information
                    Stock existingStock = stockRepo.findBySymbol(symbol);
                    if (existingStock != null) {
                        existingStock.setHigh(priceResponse.getH());
                        existingStock.setLow(priceResponse.getL());
                        existingStock.setBuy(priceResponse.getO());
                        existingStock.setSell(priceResponse.getC());
                        existingStock.setBuyChange(priceResponse.getDp());
                        existingStock.setSellChange(priceResponse.getD());
                        addPrevPrice(existingStock, priceResponse.getC());
                        shiftPrices(existingStock);
                        stockRepo.save(existingStock);
                    }
                },
                error -> {
                    // Handle error if needed
                    System.err.println("Error fetching stock price: " + error.getMessage());
                }
        );
    }

    private void addPrevPrice(Stock stock, double prevPrice) {
        stock.getPrevPrices().add(0, prevPrice);
    }

    private void shiftPrices(Stock stock) {
        List<Double> priceHistory = stock.getPrevPrices();
        for (int i = 0; i < 2; i++) {
            priceHistory.add(0, priceHistory.get(priceHistory.size() - 1));
            priceHistory.remove(priceHistory.size() - 1);
        }
    }


}
