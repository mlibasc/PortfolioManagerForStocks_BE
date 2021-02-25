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
@RequestMapping("/api/v1")
public class FXSpotController {
    @Autowired
    private FXSpotService fxSpotImpl;

    @GetMapping("/fxspots")
    public List<FXSpot> getAllFXSpots(){
        return fxSpotImpl.getAllFXSpots();
    }

    @GetMapping("/fxspot/{id}")
    public Optional<FXSpot> quoteFXSpot(@PathVariable(value = "id") Long id){
        Optional<FXSpot> fxSpot = fxSpotImpl.getFXSpot(id);
        return fxSpot;
    }

    @PostMapping("/fxspot")
    public int createFXSpot(@Valid@NotNull@RequestBody FXSpot fxSpot){
        fxSpotImpl.createFXSpot(fxSpot);
        return 1;
    }

    @PutMapping("/fxspot/{id}")
    public int updateFXSpot(@PathVariable("id")long id, @Valid@NotNull@RequestBody FXSpot fxSpot){
        fxSpotImpl.updateFXSpot(fxSpot);
        return 1;
    }

    @DeleteMapping("/fxspot/{id}")
    public int deleteFXSpot(@PathVariable(value = "id") long id, @Valid@NotNull@RequestBody FXSpot fxSpot){
        fxSpotImpl.deleteFXSpot(fxSpot);
        return 1;
    }
}
