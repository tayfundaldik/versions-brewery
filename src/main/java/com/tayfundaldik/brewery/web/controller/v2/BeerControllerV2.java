package com.tayfundaldik.brewery.web.controller.v2;

import com.tayfundaldik.brewery.services.v2.BeerServiceV2;
import com.tayfundaldik.brewery.web.model.v2.BeerDtoV2;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {
    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerServiceV2 = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@NotNull @PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerServiceV2.getBeerByID(beerId), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity handlePost (@Valid @NotNull @RequestBody BeerDtoV2 beerDto){
        BeerDtoV2 savedDto = beerServiceV2.savedBeer(beerDto);
        HttpHeaders headers =new HttpHeaders();
//        todo add hostname to url
        headers.add("Location","/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDtoV2 beerDto){

        beerServiceV2.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer (@PathVariable("beerId") UUID beerId) {beerServiceV2.deleteById(beerId);}

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
        List<String> errors =new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath()+ " : "+ constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
