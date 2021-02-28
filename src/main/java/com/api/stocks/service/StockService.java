package com.api.stocks.service;

import com.api.stocks.entity.Stock;
import com.api.stocks.repository.StockRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepo;

    public List<Stock> getAllStocks(){return stockRepo.findAll();}

    public Optional<Stock> getStock(Long id){
        Optional<Stock> stock = stockRepo.findById(id);
        return stock;
    }

    public void createStock(Stock stock){
        stockRepo.save(stock);
    }

    public void updateStock(long id, String symbol, BigDecimal price, String currency){
        Stock stock = stockRepo.findById(id).get();
        if(symbol != null){
            stock.setSymbol(symbol);
        }
        if(price != null){
            stock.setPrice(price);
        }
        if(currency != null){
            stock.setCurrency(currency);
        }
        stockRepo.save(stock);
    }

    public void deleteStock(long id){
        Stock stock = stockRepo.findById(id).get();
        stockRepo.delete(stock);
    }
}
