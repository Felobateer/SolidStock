package stocks.services.center.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String symbol;
    private String name;
    private String imgUrl;
    private String industry;
    private float price;
    private float prevPrice;
    /* HAP stands for hours ago price */
    private float HAP;
    private float twoHAP;
    private float threeHAP;
    private float yesterdayPrice;
    /*DAP stands for days ago price*/
    private float twoDAP;
    private float threeDAP;
    private float fourDAP;
    private float fiveDAP;
    private float sell;
    private float buy;
    private double change;
    private double high;
    private double low;
    private Long timeStamp;

//    private void Stock(JsonNode apiData, JsonNode apiNum) {
//        this.symbol = apiData.get("ticker").asText();
//        this.name = apiData.get("name").asText();
//        this.imgUrl = apiData.get("logo").asText();
//        this.industry = apiData.get("finnhubIndustry").asText();
//        this.price = (float) apiNum.get("c").asDouble();
//        this.prevPrice = (float) apiNum.get("pc").asDouble();
//        this.buy = (float) apiNum.get("o").asDouble();
//        this.sell = (float) apiNum.get("c").asDouble();
//        this.change = apiNum.get("d").asDouble();
//        this.high = apiNum.get("h").asDouble();
//        this.low = apiNum.get("l").asDouble();
//        this.timeStamp = apiNum.get("t").asLong();
//    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String url) {
        this.imgUrl = url;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String type) {
        this.industry = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrevPrice() {
        return prevPrice;
    }

    public void setPrevPrice(float prevPrice) {
        this.prevPrice = prevPrice;
    }

    public float getHAP() {
        return HAP;
    }

    public void setHAP(float hap) {
        this.HAP = hap;
    }

    public float getTwoHAP() {
        return twoHAP;
    }

    public void setTwoHAP(float hap) {
        this.twoHAP = hap;
    }

    public float getThreeHAP() {
        return threeHAP;
    }

    public void setThreeHAP(float hap) {
        this.threeHAP = hap;
    }

    public float getYesterdayPrice() {
        return yesterdayPrice;
    }

    public void setYesterdayPrice(float yPrice){
        this.yesterdayPrice = yPrice;
    }

    public float getTwoDAP() {
        return twoDAP;
    }

    public void setTwoDAP(float dap) {
        this.twoDAP = dap;
    }

    public float getThreeDAP() {
        return threeDAP;
    }
    public void setThreeDAP(float dap) {
        this.threeDAP = dap;
    }
    public float getFourDAP() {
        return fourDAP;
    }

    public void setFourDAP(float dap) {
        this.fourDAP = dap;
    }

    public float getFiveDAP() {
        return fiveDAP;
    }

    public void setFiveDAP(float dap) {
        this.fiveDAP = dap;
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

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
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
