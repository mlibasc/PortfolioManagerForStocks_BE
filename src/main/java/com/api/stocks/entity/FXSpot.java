package com.api.stocks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "FXSpots")
public class FXSpot {
    private long id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;

    public FXSpot(){
        this.id = 1;
        this.fromCurrency = "CAD";
        this.toCurrency = "USD";
        this.rate = new BigDecimal(1.355);
    }

    public FXSpot(@JsonProperty("id") long id, @JsonProperty("fromCurrency") String fromCurrency, @JsonProperty("toCurrency") String toCurrency,
                  @JsonProperty("rate") BigDecimal rate){
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    @Id
    @GeneratedValue
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getFromCurrency(){
        return fromCurrency;
    }
    public void setFromCurrency(String currency){
        this.fromCurrency = currency;
    }

    public String getToCurrency(){
        return toCurrency;
    }
    public void setToCurrency(String currency){
        this.toCurrency = currency;
    }

    public BigDecimal getRate(){
        return rate;
    }
    public void setRate(BigDecimal rate){
        this.rate = rate;
    }
}
