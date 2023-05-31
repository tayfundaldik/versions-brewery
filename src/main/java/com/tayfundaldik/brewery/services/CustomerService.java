package com.tayfundaldik.brewery.services;

import com.tayfundaldik.brewery.web.model.BeerDto;
import com.tayfundaldik.brewery.web.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface CustomerService {
  CustomerDTO getCustomerByID (UUID customerID);

  CustomerDTO savedCustomer(CustomerDTO customerDTO);

  void updateCustomer(UUID customerId, CustomerDTO customerDTO);

  void deleteById(UUID customerId);
}
