package com.api.stocks.controller;

import com.api.stocks.entity.FXSpot;
import com.api.stocks.repository.FXSpotRepository;
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
    private FXSpotRepository fxSpotRepo;

    @GetMapping("/fxspot")
    public List<FXSpot> getAllFXSpots(){
        return fxSpotRepo.findAll();
    }

    @GetMapping("fxspot/{id}")
    public Optional<FXSpot> quoteFXSpot(@PathVariable(value = "id") Long id){
        Optional<FXSpot> fxSpot = fxSpotRepo.findById(id);
        return fxSpot;
    }

    @PostMapping("/fxspot")
    public int createFXSpot(@Valid @NotNull @RequestBody FXSpot fxSpot){
        fxSpotRepo.save(fxSpot);
        return 1;
    }

    @PutMapping("/fxspot/{id}")
    public int updateFXSpot(FXSpot fxSpot){
        fxSpotRepo.save(fxSpot);
        return 1;
    }

    @DeleteMapping("/fxSpot/{id}")
    public int deleteFXSpot(FXSpot fxSpot){
        fxSpotRepo.delete(fxSpot);
        return 1;
    }
}
