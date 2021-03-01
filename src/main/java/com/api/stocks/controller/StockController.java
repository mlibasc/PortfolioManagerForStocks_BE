package com.api.stocks.controller;

import com.api.stocks.entity.Stock;
import com.api.stocks.service.StockService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class StockController {
    @Autowired
    private StockService stockImpl;

    @GetMapping("/stocks")
    public List<Stock> getAllStocks(){
        return stockImpl.getAllStocks();
    }

    @GetMapping("/stock/{id}")
    public Stock quoteStock(@PathVariable(value = "id") Long id){
        Stock stock = stockImpl.getStock(id);
        return stock;
    }

    @PostMapping("/stock")
    public void createStock(@Valid@NotNull@RequestBody Stock stock){
        stockImpl.createStock(stock);
    }

    @PutMapping("/stock/{id}")
    public void updateStock(@PathVariable("id") long id, @Valid@NotNull@RequestBody Stock stock){
        stockImpl.updateStock(id, stock.getSymbol(), stock.getPrice(), stock.getCurrency());
    }

    @DeleteMapping("/stock/{id}")
    public void deleteStock(@PathVariable("id") long id){
        stockImpl.deleteStock(id);
    }

    /*
    @PutMapping("/stocks/{id}")
    public void updateStock(@PathVariable(value = "id") Long id, @RequestBody Stock stockDetails)
        throws ConfigDataResourceNotFoundException{
        Stock stock = stockRepo.findById(id);

        Optional<Stock> stock = stockRepo.findById(id);
        if(stock.isPresent()){
            stock.setSymbol(stockDetails.getSymbol());
            stock.setPrice(stockDetails.getPrice());
            stock.setCurrency(stockDetails.getCurrency());
            stockRepo.save(stock);
        }
    }
     */
}
