package com.api.stocks.service;

import com.api.stocks.entity.FXSpot;
import com.api.stocks.repository.FXSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int createFXSpot(FXSpot fxSpot){
        fxSpotRepo.save(fxSpot);
        return 1;
    }

    public int updateFXSpot(FXSpot fxSpot){
        fxSpotRepo.save(fxSpot);
        return 1;
    }

    public int deleteFXSpot(FXSpot fxSpot){
        fxSpotRepo.delete(fxSpot);
        return 1;
    }

}
