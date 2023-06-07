package com.tayfundaldik.brewery.web.mappers;

import com.tayfundaldik.brewery.domain.Beer;
import com.tayfundaldik.brewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDto beertoBeerDTO(Beer beer);
    Beer beerDtotoBeer(BeerDto dto);
}
