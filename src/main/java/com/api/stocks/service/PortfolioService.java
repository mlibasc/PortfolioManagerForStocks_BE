package com.api.stocks.service;

import com.api.stocks.entity.Portfolio;
import com.api.stocks.repository.PortfolioRepository;
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

    public int createPortfolio(Portfolio portfolio){
        portfolioRepo.save(portfolio);
        return 1;
    }

    public int updatePortfolio(Portfolio portfolio){
        portfolioRepo.save(portfolio);
        return 1;
    }

    public int deletePortfolio(Portfolio portfolio){
        portfolioRepo.delete(portfolio);
        return 1;
    }
}
