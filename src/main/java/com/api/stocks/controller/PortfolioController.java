package com.api.stocks.controller;

import com.api.stocks.entity.Portfolio;
import com.api.stocks.entity.Stock;
import com.api.stocks.service.PortfolioService;
import com.api.stocks.service.StockService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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
    public Portfolio getPortfolio(@PathVariable(value = "id") long id){
        Portfolio portfolio = portfolioImpl.getPortfolio(id);
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

    @PutMapping("/portfolio/{portfolioId}/addstock/{stockId}/{units}")
    public void addStockToPortfolio(@PathVariable("portfolioId") long portfolioId,
                                    @PathVariable("stockId") long stockId,
                                    @PathVariable("units") BigDecimal unit){
        Portfolio portfolioToUpdate = portfolioImpl.getPortfolio(portfolioId);
        Stock stockToAdd = stockImpl.getStock(stockId);

        portfolioToUpdate.addStockToPortfolio(stockToAdd, unit);
        portfolioImpl.updatePortfolio(portfolioId, null, null);
    }

    @PutMapping("/portfolio/{portfolioId}/deletestock/{stockId}")
    public void deleteStockFromPortfolio(@PathVariable("portfolioId") long portfolioId,
                                         @PathVariable("stockId") long stockId){
        Portfolio portfolioToUpdate = portfolioImpl.getPortfolio(portfolioId);
        Stock stockToDelete = stockImpl.getStock(stockId);

        portfolioToUpdate.deleteStockFromPortfolio(stockToDelete);
        portfolioImpl.updatePortfolio(portfolioId, null, null);
    }

    @GetMapping("stocksinportfolio/{portfolioId}")
    public List<Stock> showStocksInPortfolio(@PathVariable("portfolioId") long portfolioId){
        Portfolio portfolio = portfolioImpl.getPortfolio(portfolioId);
        return portfolio.getStocks();
    }

    @GetMapping("valueOfPortfolio/{portfolioId}")
    public BigDecimal valueOfPortfolio(@PathVariable("portfolioId") long portfolioId){
        return portfolioImpl.getTotalValue(portfolioId);
    }
}
