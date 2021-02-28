package com.api.stocks.entity;

import com.api.stocks.service.StockService;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "Portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "clientName")
    private String clientName;
    @Column(name = "portfolioName ")
    private String portfolioName;
    @ManyToMany
    @JoinTable(
            name = "portfolios_stocks",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    @Column(name = "stocks_id")
    private List<Stock> stocks = new ArrayList<>();



    public Portfolio(){
        //this.id = 1;
        this.clientName = "Alfred";
        this.portfolioName = "investments";
        //this.stocks.add(stock);


        //this.stocks = Stream.of(stocks).collect(Collectors.toSet());
        //this.stocks.forEach(x -> x.getBooks().add(this));
    }

    public Portfolio(@JsonProperty("id") long id,
                     @JsonProperty("clientName") String clientName,
                     @JsonProperty("portfolioName") String portfolioName,
                     @JsonProperty("listOfStocks") List<Stock> stocks){
        this.id = id;
        this.clientName = clientName;
        this.portfolioName = portfolioName;
        this.stocks = stocks;
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
        stocks.add(stock);

    }
}