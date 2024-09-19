package com.vobi.demo.vault.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vobi.demo.vault.domain.TransactionType;
import com.vobi.demo.vault.dto.TransactionTypeDTO;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface TransactionTypeMapper {

  public TransactionTypeDTO transactionTypeToTransactionTypeDTO(TransactionType transactionType);

  public TransactionType transactionTypeDTOToTransactionType(TransactionTypeDTO transactionTypeDTO);

  public List<TransactionTypeDTO> listTransactionTypeToListTransactionTypeDTO(
      List<TransactionType> transactionTypes);

  public List<TransactionType> listTransactionTypeDTOToListTransactionType(
      List<TransactionTypeDTO> transactionTypeDTOs);
}
