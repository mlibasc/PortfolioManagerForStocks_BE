package com.api.stocks.entity;

import com.api.stocks.service.StockService;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String clientName;
    private String portfolioName;
    @ManyToMany
    @JoinTable(
            name = "portfolios_stocks",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private List<Stock> haveStocks = new ArrayList<>();

    public Portfolio(){
        //this.id = 1;
        this.clientName = "Alfred";
        this.portfolioName = "investments";
        long tempId = 1;
    }

    public Portfolio(@JsonProperty("id") long id, @JsonProperty("clientName") String clientName,
                     @JsonProperty("portfolioName") String portfolioName){
        this.id = id;
        this.clientName = clientName;
        this.portfolioName = portfolioName;
    }

    public long getId(){return id;}
    public void setId(Long id){this.id = id;}

    //@Column(name = "clientName", nullable = false)
    public String getClientName(){return clientName;}
    public void setClientName(String clientName){ this.clientName = clientName;}

    //@Column(name = "portfolioName", nullable = false)
    public String getPortfolioName(){return portfolioName;}
    public void setPortfolioName(String portfolioName){ this.portfolioName = portfolioName;}

    public void addStockToPortfolio(Stock stock){
        haveStocks.add(stock);
    }

}
