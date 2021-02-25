package com.api.stocks.controller;

import com.api.stocks.entity.Stock;
import com.api.stocks.repository.StockRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StockController {
    @Autowired
    private StockRepository stockRepo;

    @GetMapping("/stocks")
    public List<Stock> getAllStocks(){
        return stockRepo.findAll();
    }

    @GetMapping("/stocks/{id}")
    public Optional<Stock> quoteStock(@PathVariable(value = "id") Long id){
        Optional<Stock> stock = stockRepo.findById(id);
        return stock;
    }

    @PostMapping("/stocks")
    public int createStock(@Valid @NotNull @RequestBody Stock stock){
        stockRepo.save(stock);
        return 1;
    }

    @PutMapping("/stocks/{id}")
    public int updateStock(Stock stock){
        stockRepo.save(stock);
        return 1;
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

    @DeleteMapping("/stocks/{id}")
    public int deleteStock(Stock stock){
        stockRepo.delete(stock);
        return 1;
    }
}
