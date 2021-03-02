package com.api.stocks.entity;

import com.api.stocks.service.StockService;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @Column(name = "portfolioName")
    private String portfolioName;
    private String portfolioCurrency;
    private BigDecimal totalValueOfPortfolio;
    @Column(name = "unitOfStocks")
    @ElementCollection
    private List<BigDecimal> unitOfStocks = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "portfolios_stocks",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    @Column(name = "listOfStocks")
    private List<Stock> listOfStocks = new ArrayList<>();



    public Portfolio(){
        //this.id = 1;
        this.clientName = "Alfred";
        this.portfolioName = "investments";
        this.portfolioCurrency = "CAD";
        this.totalValueOfPortfolio = new BigDecimal(0);

        //this.stocks.add(stock);


        //this.stocks = Stream.of(stocks).collect(Collectors.toSet());
        //this.stocks.forEach(x -> x.getBooks().add(this));
    }

    public Portfolio(@JsonProperty("id") long id,
                     @JsonProperty("clientName") String clientName,
                     @JsonProperty("portfolioName") String portfolioName,
                     @JsonProperty("portfolioCurrency") String portfolioCurrency,
                     @JsonProperty("totalValueOfPortfolio") BigDecimal totalValueOfPortfolio,
                     @JsonProperty("unitOfStocks") List<BigDecimal> unitOfStocks,
                     @JsonProperty("listOfStocks") List<Stock> stocks){
        this.id = id;
        this.clientName = clientName;
        this.portfolioName = portfolioName;
        this.portfolioCurrency = portfolioCurrency;
        this.totalValueOfPortfolio = totalValueOfPortfolio;
        this.unitOfStocks = unitOfStocks;
        this.listOfStocks = stocks;
    }

    public long getId(){return id;}
    public void setId(Long id){this.id = id;}

    //@Column(name = "clientName", nullable = false)
    public String getClientName(){return clientName;}
    public void setClientName(String clientName){ this.clientName = clientName;}

    //@Column(name = "portfolioName", nullable = false)
    public String getPortfolioName(){return portfolioName;}
    public void setPortfolioName(String portfolioName){ this.portfolioName = portfolioName;}

    public String getCurrency(){return portfolioCurrency;}
    public void setPortfolioCurrency(String portfolioCurrency){this.portfolioCurrency = portfolioCurrency;}

    public BigDecimal getTotalValueOfPortfolio(){return totalValueOfPortfolio;}
    public void setTotalValueOfPortfolio(BigDecimal totalValueOfPortfolio){this.totalValueOfPortfolio = totalValueOfPortfolio;}

    public List<Stock> getStocks(){return listOfStocks;}
    public void setStocks(List<Stock> stocks){this.listOfStocks = stocks;}

    public List<BigDecimal> getUnitOfStocks(){return unitOfStocks;}
    public void setUnitOfStocks(List<BigDecimal> unitOfStocks){this.unitOfStocks = unitOfStocks;}

    public void addStockToPortfolio(Stock stock, BigDecimal unit){
        this.listOfStocks.add(stock);
        this.unitOfStocks.add(unit);
    }
    public void deleteStockFromPortfolio(Stock stock){
        this.unitOfStocks.remove(listOfStocks.indexOf(stock));
        this.listOfStocks.remove(stock);
    }
}