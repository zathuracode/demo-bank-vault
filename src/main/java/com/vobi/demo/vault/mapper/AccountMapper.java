package com.vobi.demo.vault.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.demo.vault.domain.Account;
import com.vobi.demo.vault.dto.AccountDTO;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface AccountMapper {

  @Mapping(source = "customer.custId", target = "custIdCustomer")
  public AccountDTO accountToAccountDTO(Account account);

  @Mapping(source = "custIdCustomer", target = "customer.custId")
  public Account accountDTOToAccount(AccountDTO accountDTO);

  public List<AccountDTO> listAccountToListAccountDTO(List<Account> accounts);

  public List<Account> listAccountDTOToListAccount(List<AccountDTO> accountDTOs);
}
