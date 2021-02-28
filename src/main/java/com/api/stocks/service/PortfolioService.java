package com.api.stocks.service;

import com.api.stocks.entity.Portfolio;
import com.api.stocks.entity.Stock;
import com.api.stocks.repository.PortfolioRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepo;

    public List<Portfolio> getAllPortfolios(){return portfolioRepo.findAll();}

    public Optional<Portfolio> getPortfolio(Long id){
        Optional<Portfolio> portfolio = portfolioRepo.findById(id);
        return portfolio;
    }

    public void createPortfolio(Portfolio portfolio){
        portfolioRepo.save(portfolio);
    }

    public void updatePortfolio(long id, String clientName, String portfolioName){
        Portfolio portfolio = portfolioRepo.findById(id).get();
        if(clientName != null){
            portfolio.setClientName(clientName);
        }
        if(portfolioName != null){
            portfolio.setPortfolioName(portfolioName);
        }
        portfolioRepo.save(portfolio);
    }

    public void deletePortfolio(long id){
        Portfolio portfolio = portfolioRepo.findById(id).get();
        portfolioRepo.delete(portfolio);
    }
}
