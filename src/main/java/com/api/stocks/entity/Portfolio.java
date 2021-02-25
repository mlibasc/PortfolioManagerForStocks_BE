package com.api.stocks.entity;

import com.api.stocks.service.StockService;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "Portfolios")
public class Portfolio {
    private StockService stockServiceImpl;
    private long id;
    private String clientName;
    private String portfolioName;
    @OneToMany
    private Set<Optional<Stock>> setOfStocks;

    public Portfolio(){
        this.id = 1;
        this.clientName = "Alfred";
        this.portfolioName = "investments";
        long tempId = 1;
        this.setOfStocks.add(stockServiceImpl.getStock(tempId));
    }

    public Portfolio(@JsonProperty("id") long id, @JsonProperty("clientName") String clientName,
                     @JsonProperty("portfolioName") String portfolioName,
                     @JsonProperty("listOfStocks") Set<Optional<Stock>> setOfStocks){
        this.id = id;
        this.clientName = clientName;
        this.portfolioName = portfolioName;
        this.setOfStocks = setOfStocks;
    }

    @Id
    @GeneratedValue
    public long getId(){return id;}
    public void setId(Long id){this.id = id;}

    @Column(name = "clientName", nullable = false)
    public String getClientName(){return clientName;}
    public void setClientName(String clientName){ this.clientName = clientName;}

    @Column(name = "portfolioName", nullable = false)
    public String getPortfolioName(){return portfolioName;}
    public void setPortfolioName(String portfolioName){ this.portfolioName = portfolioName;}

    @Column(name = "setOfStocks", nullable = false)
    public Set<Optional<Stock>> getSetOfStocks(){return setOfStocks;}
    public void setListOfStocks(Set<Optional<Stock>> setOfStocks){this.setOfStocks = setOfStocks;}
}
