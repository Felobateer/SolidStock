package stocks.services.center.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    private String symbol;
    private String name;
    private String imgUrl;
    private String industry;
    private float price;
    private List<Double> prevPrices = new ArrayList<>();
    private float sell;
    private float buy;
    private double buyChange;
    private double sellChange;
    private double high;
    private double low;
    private long timeStamp;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLogo() {
        return imgUrl;
    }

    public void setLogo(String url) {
        this.imgUrl = url;
    }

    public String getFinnhubIndustry() {
        return industry;
    }

    public void setFinnhubIndustry(String type) {
        this.industry = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getBuyChange() {
        return buyChange;
    }

    public void setBuyChange(double buyChange) {
        this.buyChange = buyChange;
    }

    public double getSellChange() {return sellChange;}

    public void setSellChange(double sellChange) {
        this.sellChange = sellChange;
    }

    public float getSell() {return sell;}

    public void setSell(float sell) {
        this.sell = sell;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public List<Double> getPrevPrices() {
        return prevPrices;
    }

    public void setPrevPrices(List<Double> prevPrices) {
        this.prevPrices = prevPrices;
    }

    public void addPrevPrices(double prevPrice) {
        this.prevPrices.add(prevPrice);
    }
    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
