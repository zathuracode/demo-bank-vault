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

import com.vobi.demo.vault.domain.DocumentType;
import com.vobi.demo.vault.dto.DocumentTypeDTO;
import com.vobi.demo.vault.entity.service.DocumentTypeService;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.mapper.DocumentTypeMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@RestController
@RequestMapping("/api/v1/documentType")
@Slf4j
public class DocumentTypeRestController {

  @Autowired private DocumentTypeService documentTypeService;

  @Autowired private DocumentTypeMapper documentTypeMapper;

  @GetMapping(value = "/{dotyId}")
  public ResponseEntity<?> findById(@PathVariable("dotyId") Integer dotyId)
      throws VortexbirdException {
    log.debug("Request to findById() DocumentType");

    Optional<DocumentType> optionalDocumentType = documentTypeService.findById(dotyId);

    DocumentType documentType =
        optionalDocumentType.isPresent() ? optionalDocumentType.get() : null;

    return ResponseEntity.ok().body(documentTypeMapper.documentTypeToDocumentTypeDTO(documentType));
  }

  @GetMapping()
  public ResponseEntity<?> findAll() throws VortexbirdException {
    log.debug("Request to findAll() DocumentType");

    return ResponseEntity.ok()
        .body(
            documentTypeMapper.listDocumentTypeToListDocumentTypeDTO(
                documentTypeService.findAll()));
  }

  @PostMapping()
  public ResponseEntity<?> save(@Valid @RequestBody DocumentTypeDTO documentTypeDTO)
      throws VortexbirdException {
    log.debug("Request to save DocumentType: {}", documentTypeDTO);

    DocumentType documentType = documentTypeMapper.documentTypeDTOToDocumentType(documentTypeDTO);
    documentType = documentTypeService.save(documentType);
    return ResponseEntity.ok().body(documentTypeMapper.documentTypeToDocumentTypeDTO(documentType));
  }

  @PutMapping()
  public ResponseEntity<?> update(@Valid @RequestBody DocumentTypeDTO documentTypeDTO)
      throws VortexbirdException {
    log.debug("Request to update DocumentType: {}", documentTypeDTO);

    DocumentType documentType = documentTypeMapper.documentTypeDTOToDocumentType(documentTypeDTO);
    documentType = documentTypeService.update(documentType);

    return ResponseEntity.ok().body(documentTypeMapper.documentTypeToDocumentTypeDTO(documentType));
  }

  @DeleteMapping(value = "/{dotyId}")
  public ResponseEntity<?> delete(@PathVariable("dotyId") Integer dotyId)
      throws VortexbirdException {
    log.debug("Request to delete DocumentType");

    documentTypeService.deleteById(dotyId);

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(documentTypeService.count());
  }
}
