package com.api.stocks.service;

import com.api.stocks.entity.Stock;
import com.api.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int createStock(Stock stock){
        stockRepo.save(stock);
        return 1;
    }

    public int updateStock(Stock stock){
        stockRepo.save(stock);
        return 1;
    }

    public int deleteStock(Stock stock){
        stockRepo.delete(stock);
        return 1;
    }
}
