package com.tayfundaldik.brewery.services;

import com.tayfundaldik.brewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerByID (UUID beerID);

    BeerDto savedBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteById(UUID beerId);
}
