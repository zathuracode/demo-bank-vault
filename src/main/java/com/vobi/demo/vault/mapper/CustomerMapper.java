package com.vobi.demo.vault.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.demo.vault.domain.Customer;
import com.vobi.demo.vault.dto.CustomerDTO;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface CustomerMapper {

  @Mapping(source = "documentType.dotyId", target = "dotyIdDocumentType")
  public CustomerDTO customerToCustomerDTO(Customer customer);

  @Mapping(source = "dotyIdDocumentType", target = "documentType.dotyId")
  public Customer customerDTOToCustomer(CustomerDTO customerDTO);

  public List<CustomerDTO> listCustomerToListCustomerDTO(List<Customer> customers);

  public List<Customer> listCustomerDTOToListCustomer(List<CustomerDTO> customerDTOs);
}
