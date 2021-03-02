package com.api.stocks.service;

import com.api.stocks.entity.FXSpot;
import com.api.stocks.entity.Portfolio;
import com.api.stocks.entity.Stock;
import com.api.stocks.repository.FXSpotRepository;
import com.api.stocks.repository.PortfolioRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepo;
    @Autowired
    private FXSpotService fxSpotImpl;

    public List<Portfolio> getAllPortfolios(){return portfolioRepo.findAll();}

    public Portfolio getPortfolio(Long id){
        Portfolio portfolio = portfolioRepo.findById(id).get();
        return portfolio;
    }

    public void createPortfolio(Portfolio portfolio){
        portfolioRepo.save(portfolio);
    }

    public void updatePortfolio(long id, String clientName, String portfolioName, String portfoliocurrency){
        Portfolio portfolio = portfolioRepo.findById(id).get();
        if(clientName != null){
            portfolio.setClientName(clientName);
        }
        if(portfolioName != null){
            portfolio.setPortfolioName(portfolioName);
        }
        if(portfoliocurrency != null){
            portfolio.setPortfolioCurrency(portfoliocurrency);
        }
        BigDecimal totalValue = getTotalValue(portfolio.getId()).setScale(2, RoundingMode.FLOOR);;
        portfolio.setTotalValueOfPortfolio(totalValue);
        portfolioRepo.save(portfolio);
    }

    public void deletePortfolio(long id){
        Portfolio portfolio = portfolioRepo.findById(id).get();
        portfolioRepo.delete(portfolio);
    }

    public BigDecimal convertPrice(BigDecimal priceOfStock, String stockCurrency, String portfolioCurrency){
        if(stockCurrency == portfolioCurrency){
            return priceOfStock;
        }
        try{
            FXSpot fxSpot = fxSpotImpl.getFXSpotByCurrency(stockCurrency, portfolioCurrency);
            return priceOfStock.multiply(fxSpot.getRate()).setScale(2, RoundingMode.FLOOR);
        }catch(NullPointerException e){
            System.out.print("No FXSpot available to make conversion");
        }
        return new BigDecimal(-1);
    }

    public BigDecimal getTotalValue(long id){
        // Grab the portfolio from the repo by the Id
        Portfolio portfolio = portfolioRepo.findById(id).get();
        // Use a variable to keep track of portfolio total value
        BigDecimal totalValue = new BigDecimal(0);

        // Get the list of stocks and units per stock
        List<Stock> listOfStocks = portfolio.getStocks();
        List<BigDecimal> unitOfStocks = portfolio.getUnitOfStocks();

        // Iterate through the list of stocks to calculate the total portfolio value
        for(int i = 0; i < listOfStocks.size(); i++){
            // Calculate the total value of purchased stock
            BigDecimal priceOfStock =  listOfStocks.get(i).getPrice().multiply(unitOfStocks.get(i));
            totalValue = totalValue.add(convertPrice(priceOfStock, listOfStocks.get(i).getCurrency(), portfolio.getCurrency()));
        }

        return totalValue;
    }
}
