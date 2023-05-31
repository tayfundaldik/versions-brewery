package com.tayfundaldik.brewery.services.v2;

import com.tayfundaldik.brewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {

    BeerDtoV2 getBeerByID(UUID beerId);

    BeerDtoV2 savedBeer(BeerDtoV2 beerDto);

    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteById(UUID beerId);
}
