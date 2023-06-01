package com.tayfundaldik.brewery.web.controller;


import com.tayfundaldik.brewery.services.CustomerService;
import com.tayfundaldik.brewery.web.model.CustomerDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;

   public CustomerController (CustomerService customerService){
       this.customerService = customerService;
   }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> CustomerEntity (@PathVariable("customerId") UUID customerID){

        return new ResponseEntity<>(customerService.getCustomerByID(customerID), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handleCustPost (@Valid @NotNull @RequestBody CustomerDTO customerDTO){
       CustomerDTO savedDto = customerService.savedCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/"+savedDto.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }
    @PutMapping("/{customerId}")
    public ResponseEntity handleUpdate(@PathVariable("customerId") UUID customerId,@Valid @RequestBody CustomerDTO customerDTO){
            customerService.updateCustomer(customerId,customerDTO);
            return new ResponseEntity((HttpStatus.NO_CONTENT));
    }
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("customerId") UUID customerId){customerService.deleteById(customerId);}

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> ErrorHandler(ConstraintViolationException e){
        List<String> errors =new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath()+ " : "+ constraintViolation.getMessage());
    });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
   }
}
