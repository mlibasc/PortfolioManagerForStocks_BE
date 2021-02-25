package com.api.stocks.controller;

import com.api.stocks.entity.Portfolio;
import com.api.stocks.service.PortfolioService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioImpl;

    @GetMapping("/portfolios")
    public List<Portfolio> getAllStocks(){return portfolioImpl.getAllPortfolios();}

    @GetMapping("/portfolio/{id}")
    public Optional<Portfolio> getPortfolio(@PathVariable(value = "id") Long id){
        Optional<Portfolio> portfolio = portfolioImpl.getPortfolio(id);
        return portfolio;
    }

    @PostMapping("/portfolio")
    public int createPortfolio(@Valid@NotNull@RequestBody Portfolio portfolio){
        portfolioImpl.createPortfolio(portfolio);
        return 1;
    }

    @PutMapping("/portfolio/{id}")
    public int updatePortfolio(@PathVariable("id") long id, @Valid@NotNull@RequestBody Portfolio portfolio){
        portfolioImpl.updatePortfolio(portfolio);
        return 1;
    }

    @DeleteMapping("/portfolio/{id}")
    public int deletePortfolio(@PathVariable(value = "id") long id, @Valid@NotNull@RequestBody Portfolio portfolio){
        portfolioImpl.deletePortfolio(portfolio);
        return 1;
    }
}
