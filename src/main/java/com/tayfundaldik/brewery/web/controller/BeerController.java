package com.tayfundaldik.brewery.web.controller;

import com.tayfundaldik.brewery.services.BeerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tayfundaldik.brewery.web.model.BeerDto;

import java.util.UUID;

@Deprecated
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerService.getBeerByID(beerId), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity handlePost (@Valid @RequestBody BeerDto beerDto){
        BeerDto savedDto = beerService.savedBeer(beerDto);
        HttpHeaders headers =new HttpHeaders();
//        todo add hostname to url
        headers.add("Location","/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto){

        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer (@PathVariable("beerId") UUID beerId) {beerService.deleteById(beerId);}
}
