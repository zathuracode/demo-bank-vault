package com.vobi.demo.vault.entity.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.demo.vault.domain.Customer;
import com.vobi.demo.vault.domain.DocumentType;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.exception.ZMessManager;
import com.vobi.demo.vault.repository.DocumentTypeRepository;
import com.vobi.demo.vault.utility.Utilities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@Scope("singleton")
@Service
@Slf4j
class DocumentTypeServiceImpl implements DocumentTypeService {

  @Autowired private DocumentTypeRepository documentTypeRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(DocumentType documentType) throws ConstraintViolationException {

    Set<ConstraintViolation<DocumentType>> constraintViolations = validator.validate(documentType);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {
    return documentTypeRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<DocumentType> findAll() {
    log.debug("finding all DocumentType instances");
    return documentTypeRepository.findAll();
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public DocumentType save(DocumentType entity) throws VortexbirdException {
    log.debug("saving DocumentType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("DocumentType");
    }

    validate(entity);

    return documentTypeRepository.save(entity);
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void delete(DocumentType entity) throws VortexbirdException {
    log.debug("deleting DocumentType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("DocumentType");
    }

    if (entity.getDotyId() == null) {
      throw new ZMessManager().new EmptyFieldException("dotyId");
    }

    if (!documentTypeRepository.existsById(entity.getDotyId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    findById(entity.getDotyId())
        .ifPresent(
            entidad -> {
              List<Customer> customers = entidad.getCustomers();
              if (Utilities.validationsList(customers)) {
                throw new ZMessManager().new DeletingException("customers");
              }
            });

    documentTypeRepository.deleteById(entity.getDotyId());
    log.debug("delete DocumentType successful");
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void deleteById(Integer id) throws VortexbirdException {
    log.debug("deleting DocumentType instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("dotyId");
    }
    Optional<DocumentType> optionalDocumentType = documentTypeRepository.findById(id);
    if (optionalDocumentType.isPresent()) {
      delete(optionalDocumentType.get());
    }
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public DocumentType update(DocumentType entity) throws VortexbirdException {

    log.debug("updating DocumentType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("DocumentType");
    }

    validate(entity);

    if (!documentTypeRepository.existsById(entity.getDotyId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    return documentTypeRepository.save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<DocumentType> findById(Integer dotyId) {
    log.debug("getting DocumentType instance");
    return documentTypeRepository.findById(dotyId);
  }
}
