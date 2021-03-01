package com.api.stocks.controller;

import com.api.stocks.entity.FXSpot;
import com.api.stocks.service.FXSpotService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class FXSpotController {
    @Autowired
    private FXSpotService fxSpotImpl;

    @GetMapping("/fxspots")
    public List<FXSpot> getAllFXSpots(){
        return fxSpotImpl.getAllFXSpots();
    }

    @GetMapping("/fxspot/{id}")
    public FXSpot quoteFXSpot(@PathVariable(value = "id") long id){
        FXSpot fxSpot = fxSpotImpl.getFXSpot(id);
        return fxSpot;
    }

    @PostMapping("/fxspot")
    public void createFXSpot(@Valid@NotNull@RequestBody FXSpot fxSpot){
        fxSpotImpl.createFXSpot(fxSpot);
    }

    @PutMapping("/fxspot/{id}")
    public void updateFXSpot(@PathVariable("id") long id, @Valid@NotNull@RequestBody FXSpot fxSpot){
        fxSpotImpl.updateFXSpot(id, fxSpot.getFromCurrency(), fxSpot.getToCurrency(), fxSpot.getRate());
    }

    @DeleteMapping("/fxspot/{id}")
    public void deleteFXSpot(@PathVariable("id") long id){
        fxSpotImpl.deleteFXSpot(id);
    }
}
