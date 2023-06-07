package com.tayfundaldik.brewery.web.mappers;

import com.tayfundaldik.brewery.domain.Customer;
import com.tayfundaldik.brewery.web.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDTO customertoCustomerDto(Customer customer);
    Customer customerDtocustomer(CustomerDTO dto);
}
