package stocks.services.center.domain;

import java.util.Date;

public class StockExchange {
    private float sell;
    private float buy;
    private String symbol;
    private double assets;
    private Date timestamp;

    // Constructors, getters, and setters

    public StockExchange() {
        // Default constructor
    }

    public StockExchange(float sell, float buy, String symbol, double assets, Date timestamp) {
        this.sell = sell;
        this.buy = buy;
        this.symbol = symbol;
        this.assets = assets;
        this.timestamp = timestamp;
    }

    public float getSell() {
        return sell;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAssets() {
        return assets;
    }

    public void setAssets(double assets) {
        this.assets = assets;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
