package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import stocks.services.center.repo.StockRepo;

import java.lang.reflect.Array;

@Service
public class StockServices {
    private final StockRepo stockRepo;
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
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = this.apiKey;

        for(String stock: this.apiStocks) {
            String apiInfoUrl = String.format(apiInfoTemplate, stock, apiKey);
            String apiPriceUrl = String.format(apiPriceTemplate, stock, apiKey);

            StockInfoResponse infoResponse = restTemplate.getForObject(apiInfoUrl, StockInfoResponse.class);
            if (infoResponse != null) {
                Stock stockEntity = new Stock();
                stockEntity.setName(infoResponse.getName());
                stockEntity.setSymbol(infoResponse.getSymbol());
                stockEntity.setIndustry(infoResponse.getIndustry());

                // Fetch stock price
                StockPriceResponse priceResponse = restTemplate.getForObject(apiPriceUrl, StockPriceResponse.class);
                if (priceResponse != null) {
                    stockEntity.setPrice(priceResponse.getC());
                    stockEntity.setPrevPrice(priceResponse.getPc());
                }
        }
    }
}
