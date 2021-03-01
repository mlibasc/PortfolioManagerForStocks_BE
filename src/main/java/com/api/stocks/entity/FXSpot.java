package com.api.stocks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FXSpots")
public class FXSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "fromCurrency")
    private String fromCurrency;
    @Column(name = "toCurrency")
    private String toCurrency;
    @Column(name = "rate")
    private BigDecimal rate;

    public FXSpot(){
        //this.id = 1;
        this.fromCurrency = "CAD";
        this.toCurrency = "USD";
        this.rate = new BigDecimal(1.355);
    }

    public FXSpot(@JsonProperty("id") long id,
                  @JsonProperty("fromCurrency") String fromCurrency,
                  @JsonProperty("toCurrency") String toCurrency,
                  @JsonProperty("rate") BigDecimal rate){
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    //@Column(name = "fromCurrency", nullable = false)
    public String getFromCurrency(){
        return fromCurrency;
    }
    public void setFromCurrency(String currency){
        this.fromCurrency = currency;
    }

    //@Column(name = "toCurrency", nullable = false)
    public String getToCurrency(){
        return toCurrency;
    }
    public void setToCurrency(String currency){
        this.toCurrency = currency;
    }

    //@Column(name = "rate", nullable = false)
    public BigDecimal getRate(){
        return rate;
    }
    public void setRate(BigDecimal rate){
        this.rate = rate;
    }

}
