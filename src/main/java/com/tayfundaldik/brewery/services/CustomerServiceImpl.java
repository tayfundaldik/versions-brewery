package com.tayfundaldik.brewery.services;

import com.tayfundaldik.brewery.web.model.BeerDto;
import com.tayfundaldik.brewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO getCustomerByID(UUID customerID) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Max")
                .build();
    }

    @Override
    public CustomerDTO savedCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {

    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("Deleting a beer...");
    }
}
