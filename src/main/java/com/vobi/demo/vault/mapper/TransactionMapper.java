package com.vobi.demo.vault.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.demo.vault.domain.Transaction;
import com.vobi.demo.vault.dto.TransactionDTO;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface TransactionMapper {

  @Mapping(source = "users.userEmail", target = "userEmailUsers")
  @Mapping(source = "transactionType.trtyId", target = "trtyIdTransactionType")
  @Mapping(source = "account.accoId", target = "accoIdAccount")
  public TransactionDTO transactionToTransactionDTO(Transaction transaction);

  @Mapping(source = "userEmailUsers", target = "users.userEmail")
  @Mapping(source = "trtyIdTransactionType", target = "transactionType.trtyId")
  @Mapping(source = "accoIdAccount", target = "account.accoId")
  public Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);

  public List<TransactionDTO> listTransactionToListTransactionDTO(List<Transaction> transactions);

  public List<Transaction> listTransactionDTOToListTransaction(
      List<TransactionDTO> transactionDTOs);
}
