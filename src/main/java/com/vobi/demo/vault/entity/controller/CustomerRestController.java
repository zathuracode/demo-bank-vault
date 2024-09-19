package com.vobi.demo.vault.entity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.demo.vault.domain.Customer;
import com.vobi.demo.vault.dto.CustomerDTO;
import com.vobi.demo.vault.entity.service.CustomerService;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.mapper.CustomerMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerRestController {

  @Autowired private CustomerService customerService;

  @Autowired private CustomerMapper customerMapper;

  @GetMapping(value = "/{custId}")
  public ResponseEntity<?> findById(@PathVariable("custId") Integer custId)
      throws VortexbirdException {
    log.debug("Request to findById() Customer");

    Optional<Customer> optionalCustomer = customerService.findById(custId);

    Customer customer = optionalCustomer.isPresent() ? optionalCustomer.get() : null;

    return ResponseEntity.ok().body(customerMapper.customerToCustomerDTO(customer));
  }

  @GetMapping()
  public ResponseEntity<?> findAll() throws VortexbirdException {
    log.debug("Request to findAll() Customer");

    return ResponseEntity.ok()
        .body(customerMapper.listCustomerToListCustomerDTO(customerService.findAll()));
  }

  @PostMapping()
  public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO customerDTO)
      throws VortexbirdException {
    log.debug("Request to save Customer: {}", customerDTO);

    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    customer = customerService.save(customer);
    return ResponseEntity.ok().body(customerMapper.customerToCustomerDTO(customer));
  }

  @PutMapping()
  public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO)
      throws VortexbirdException {
    log.debug("Request to update Customer: {}", customerDTO);

    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    customer = customerService.update(customer);

    return ResponseEntity.ok().body(customerMapper.customerToCustomerDTO(customer));
  }

  @DeleteMapping(value = "/{custId}")
  public ResponseEntity<?> delete(@PathVariable("custId") Integer custId)
      throws VortexbirdException {
    log.debug("Request to delete Customer");

    customerService.deleteById(custId);

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(customerService.count());
  }
}
