package com.api.stocks.controller;

import com.api.stocks.entity.Portfolio;
import com.api.stocks.entity.Stock;
import com.api.stocks.service.PortfolioService;
import com.api.stocks.service.StockService;
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

    @Autowired
    private StockService stockImpl;

    @GetMapping("/portfolios")
    public List<Portfolio> getAllStocks(){return portfolioImpl.getAllPortfolios();}

    @GetMapping("/portfolio/{id}")
    public Optional<Portfolio> getPortfolio(@PathVariable(value = "id") long id){
        Optional<Portfolio> portfolio = portfolioImpl.getPortfolio(id);
        return portfolio;
    }

    @PostMapping("/portfolio")
    public void createPortfolio(@Valid@NotNull@RequestBody Portfolio portfolio){
        portfolioImpl.createPortfolio(portfolio);
    }

    @PutMapping("/portfolio/{id}")
    public void updatePortfolio(@PathVariable("id") long id, @Valid@NotNull@RequestBody Portfolio portfolio){
        portfolioImpl.updatePortfolio(id, portfolio.getClientName(), portfolio.getPortfolioName());
    }

    @DeleteMapping("/portfolio/{id}")
    public void deletePortfolio(@PathVariable("id") long id){
        portfolioImpl.deletePortfolio(id);
    }

    @PutMapping("/portfolio/{portfolioId}/stock/{stockId}")
    public void addStockToPortfolio(@PathVariable(value = "portfolioId") long portfolioId,
                                   @PathVariable(value = "stockId") long stockId){
        Portfolio portfolioToUpdate = portfolioImpl.getPortfolio(portfolioId).get();
        Stock stockToAdd = stockImpl.getStock(stockId).get();

        portfolioToUpdate.addStockToPortfolio(stockToAdd);
        portfolioImpl.updatePortfolio(portfolioId, null, null);
    }
}
