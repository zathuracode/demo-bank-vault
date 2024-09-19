package com.vobi.demo.vault.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vobi.demo.vault.domain.DocumentType;
import com.vobi.demo.vault.dto.DocumentTypeDTO;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface DocumentTypeMapper {

  public DocumentTypeDTO documentTypeToDocumentTypeDTO(DocumentType documentType);

  public DocumentType documentTypeDTOToDocumentType(DocumentTypeDTO documentTypeDTO);

  public List<DocumentTypeDTO> listDocumentTypeToListDocumentTypeDTO(
      List<DocumentType> documentTypes);

  public List<DocumentType> listDocumentTypeDTOToListDocumentType(
      List<DocumentTypeDTO> documentTypeDTOs);
}
