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
@RequestMapping("/api/v1")
public class StockController {
    @Autowired
    private StockService stockImpl;

    @GetMapping("/stocks")
    public List<Stock> getAllStocks(){
        return stockImpl.getAllStocks();
    }

    @GetMapping("/stock/{id}")
    public Optional<Stock> quoteStock(@PathVariable(value = "id") Long id){
        Optional<Stock> stock = stockImpl.getStock(id);
        return stock;
    }

    @PostMapping("/stock")
    public int createStock(@Valid@NotNull@RequestBody Stock stock){
        stockImpl.createStock(stock);
        return 1;
    }

    @PutMapping("/stock/{id}")
    public int updateStock(Stock stock){
        stockImpl.updateStock(stock);
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

    @DeleteMapping("/stock/{id}")
    public int deleteStock(Stock stock){
        stockImpl.deleteStock(stock);
        return 1;
    }
}
