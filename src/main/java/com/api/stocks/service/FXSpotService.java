package com.api.stocks.service;

import com.api.stocks.entity.FXSpot;
import com.api.stocks.repository.FXSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FXSpotService {
    @Autowired
    private FXSpotRepository fxSpotRepo;

    public List<FXSpot> getAllFXSpots(){
        return fxSpotRepo.findAll();
    }

    public Optional<FXSpot> getFXSpot(Long id){
        Optional<FXSpot> fxSpot = fxSpotRepo.findById(id);
        return fxSpot;
    }

    public void createFXSpot(FXSpot fxSpot){
        fxSpotRepo.save(fxSpot);
    }

    public void updateFXSpot(long id, String fromCurrency, String toCurrency, BigDecimal rate){
        FXSpot fxSpot = fxSpotRepo.findById(id).get();
        if(fromCurrency != null){
            fxSpot.setFromCurrency(fromCurrency);
        }
        if(toCurrency != null){
            fxSpot.setToCurrency(toCurrency);
        }
        if(rate != null){
            fxSpot.setRate(rate);
        }
        fxSpotRepo.save(fxSpot);
    }

    public void deleteFXSpot(long id){
        FXSpot fxSpot = fxSpotRepo.findById(id).get();
        fxSpotRepo.delete(fxSpot);
    }

}
