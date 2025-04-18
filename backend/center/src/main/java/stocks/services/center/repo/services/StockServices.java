package stocks.services.center.repo.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import stocks.services.center.domain.Stock;
import stocks.services.center.domain.StockPriceResponse;
import stocks.services.center.repo.StockRepo;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static reactor.core.publisher.Flux.*;


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

//        fetchData();
    }

    @PostConstruct
    public void onStart() {
        fetchData();
    }

    @Transactional
    public void fetchData() {
        String apiKey = this.apiKey;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


        try {
            for (String stock : this.apiStocks) {
                fetchStockInfo(stock, apiKey);

                // Schedule the fetchStockPrice task at a fixed delay (executes once)
                ScheduledFuture<?> future = scheduler.schedule(() -> fetchStockPrice(stock, apiKey), 10, TimeUnit.SECONDS);

                // Optionally, you can cancel the task after the first execution
                scheduler.schedule(() -> future.cancel(false), 11, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            System.err.println("Error updating stock data: " + e.getMessage());
        } finally {
            // Shut down the scheduler after all tasks are scheduled
            scheduler.shutdown();
        }

    }


    @Transactional
    private void fetchStockInfo(String sym, String apiKey) {
        String apiInfoUrl = String.format(apiInfoTemplate, sym, apiKey);
        Stock existingStock = stockRepo.findBySymbol(sym);
        boolean present = existingStock != null;

        Mono<Stock> response = webClient.get()
                .uri(apiInfoUrl)
                .retrieve()
                .bodyToMono(Stock.class);

        response.subscribe(
                infoResponse -> {
                    if(!present) {
                        Stock stockInfo = new Stock();
                        stockInfo.setName(infoResponse.getName());
                        stockInfo.setSymbol(sym);
                        stockInfo.setFinnhubIndustry(infoResponse.getFinnhubIndustry());
                        stockInfo.setLogo(infoResponse.getLogo());
                        stockRepo.save(stockInfo);
                    }
                    }, error -> {
                    System.err.println("Error fetching stock info: " + error.getMessage());
                }
        );
    }

    @Transactional
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

    public void updateStockPrice() {
        List<Stock> stocks = stockRepo.findAll();

        Flux.interval(Duration.ofSeconds(2)) // Introduce a delay of 2 second between each run
                .take(stocks.size()) // Limit the number of runs to the number of stocks
                .flatMap(index -> {
                    Stock stock = stocks.get(index.intValue());
                    String apiPriceUrl = String.format(this.apiPriceTemplate, stock.getSymbol(), apiKey);
                    return webClient.get().uri(apiPriceUrl).retrieve().bodyToMono(StockPriceResponse.class)
                            .doOnNext(priceResponse -> {
                                stock.setHigh(priceResponse.getH());
                                stock.setLow(priceResponse.getL());
                                stock.setBuy(priceResponse.getO());
                                stock.setSell(priceResponse.getC());
                                stock.setBuyChange(priceResponse.getDp());
                                stock.setSellChange(priceResponse.getD());
                                addPrevPrice(stock, priceResponse.getC());
                                shiftPrices(stock);
                            })
                            .onErrorResume(error -> {
                                System.err.println("Error updating stock price for " + stock.getSymbol() + error);
                                return Mono.empty(); // Continue with other stocks even if one fails
                            });
                })
                .doOnComplete(() -> stockRepo.saveAll(stocks))
                .collectList()
                .block();
    }
}
