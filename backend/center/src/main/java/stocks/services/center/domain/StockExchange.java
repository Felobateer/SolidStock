package stocks.services.center.domain;

import java.util.Date;

public interface StockExchange {
    record StockInfo(float sell, float buy, double assets, Date timestamp) {}
}
