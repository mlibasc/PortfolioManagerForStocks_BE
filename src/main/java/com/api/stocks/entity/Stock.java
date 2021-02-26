package com.api.stocks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String symbol;
    private BigDecimal price;
    private String currency;
    @ManyToMany(mappedBy = "haveStocks")
    private List<Portfolio> inPortfolios = new ArrayList<>();

    public Stock(){
        //this.id = 1;
        this.symbol = "GME";
        this.price = new BigDecimal(168);
        this.currency = "USD";
    }

    public Stock(@JsonProperty("id") long id, @JsonProperty("symbol") String symbol, @JsonProperty("price") BigDecimal price, @JsonProperty("currency") String currency){
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //@Column(name = "symbol", nullable = false)
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    //@Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //@Column(name = "currency", nullable = false)
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void addPortfolio(Portfolio portfolio){
        inPortfolios.add(portfolio);
    }
}
