package com.vobi.demo.vault.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.demo.vault.domain.RegisteredAccount;
import com.vobi.demo.vault.dto.RegisteredAccountDTO;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface RegisteredAccountMapper {

  @Mapping(source = "customer.custId", target = "custIdCustomer")
  @Mapping(source = "account.accoId", target = "accoIdAccount")
  public RegisteredAccountDTO registeredAccountToRegisteredAccountDTO(
      RegisteredAccount registeredAccount);

  @Mapping(source = "custIdCustomer", target = "customer.custId")
  @Mapping(source = "accoIdAccount", target = "account.accoId")
  public RegisteredAccount registeredAccountDTOToRegisteredAccount(
      RegisteredAccountDTO registeredAccountDTO);

  public List<RegisteredAccountDTO> listRegisteredAccountToListRegisteredAccountDTO(
      List<RegisteredAccount> registeredAccounts);

  public List<RegisteredAccount> listRegisteredAccountDTOToListRegisteredAccount(
      List<RegisteredAccountDTO> registeredAccountDTOs);
}
