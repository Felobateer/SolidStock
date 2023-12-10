package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stocks.services.center.domain.Stock;
import stocks.services.center.repo.StockRepo;




@Service
public class StockServices {
    private final StockRepo stockRepo;
    private final WebClient webClient;
    private final String apiInfoTemplate = "https://finnhub.io/api/v1/stock/profile2?symbol=%s&token=%s";
    private final String apiPriceTemplate = "https://finnhub.io/api/v1/quote?symbol=%s&token=%s";
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
    public StockServices(StockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }

    private void fetchData() {
        String apiKey = this.apiKey;


        for(String stock: this.apiStocks) {
            fetchStockInfo(stock, apiKey);
            fetchStockPrice(stock, apiKey);
        }
    }

    private void fetchStockInfo(String symbol, String apiKey) {
        String apiInfoUrl = String.format(apiInfoTemplate, symbol, apiKey);

        Mono<StockInfoResponse> responseMono = webClient.get().uri(apiInfoUrl).retrieve().bodyToMono(StockInfoResponse.class);

        responseMono.subscribe(
                infoResponse -> {
                    Stock stockEntity = new Stock();
                    stockEntity.setName(infoResponse.getName());
                    stockEntity.setSymbol(infoResponse.getSymbol());
                    stockEntity.setIndustry(infoResponse.getIndustry());

                    // Save stock entity to the repository
                    stockRepo.save(stockEntity);
                },
                error -> {
                    // Handle error if needed
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
                        existingStock.setPrice(priceResponse.getC());
                        existingStock.setPrevPrice(priceResponse.getPc());
                        stockRepo.save(existingStock);
                    }
                },
                error -> {
                    // Handle error if needed
                    System.err.println("Error fetching stock price: " + error.getMessage());
                }
        );
    }
}
