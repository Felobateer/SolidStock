package stocks.services.center.domain;

import com.fasterxml.jackson.databind.JsonNode;
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

    private void importData(JsonNode apiData, JsonNode apiNum) {
        this.symbol = apiData.get("ticker").asText();
        this.name = apiData.get("name").asText();
        this.imgUrl = apiData.get("logo").asText();
        this.industry = apiData.get("finnhubIndustry").asText();
        this.price = (float) apiNum.get("c").asDouble();
        this.prevPrice = (float) apiNum.get("pc").asDouble();
        this.buy = (float) apiNum.get("o").asDouble();
        this.sell = (float) apiNum.get("c").asDouble();
        this.change = apiNum.get("d").asDouble();
        this.high = apiNum.get("h").asDouble();
        this.low = apiNum.get("l").asDouble();
        this.timeStamp = apiNum.get("t").asLong();
        

    }
}
