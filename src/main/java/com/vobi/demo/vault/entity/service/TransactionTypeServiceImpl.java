package com.vobi.demo.vault.entity.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.demo.vault.domain.Transaction;
import com.vobi.demo.vault.domain.TransactionType;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.exception.ZMessManager;
import com.vobi.demo.vault.repository.TransactionTypeRepository;
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
class TransactionTypeServiceImpl implements TransactionTypeService {

  @Autowired private TransactionTypeRepository transactionTypeRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(TransactionType transactionType) throws ConstraintViolationException {

    Set<ConstraintViolation<TransactionType>> constraintViolations =
        validator.validate(transactionType);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {
    return transactionTypeRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<TransactionType> findAll() {
    log.debug("finding all TransactionType instances");
    return transactionTypeRepository.findAll();
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public TransactionType save(TransactionType entity) throws VortexbirdException {
    log.debug("saving TransactionType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("TransactionType");
    }

    validate(entity);

    return transactionTypeRepository.save(entity);
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void delete(TransactionType entity) throws VortexbirdException {
    log.debug("deleting TransactionType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("TransactionType");
    }

    if (entity.getTrtyId() == null) {
      throw new ZMessManager().new EmptyFieldException("trtyId");
    }

    if (!transactionTypeRepository.existsById(entity.getTrtyId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    findById(entity.getTrtyId())
        .ifPresent(
            entidad -> {
              List<Transaction> transactions = entidad.getTransactions();
              if (Utilities.validationsList(transactions)) {
                throw new ZMessManager().new DeletingException("transactions");
              }
            });

    transactionTypeRepository.deleteById(entity.getTrtyId());
    log.debug("delete TransactionType successful");
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void deleteById(Integer id) throws VortexbirdException {
    log.debug("deleting TransactionType instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("trtyId");
    }
    Optional<TransactionType> optionalTransactionType = transactionTypeRepository.findById(id);
    if (optionalTransactionType.isPresent()) {
      delete(optionalTransactionType.get());
    }
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public TransactionType update(TransactionType entity) throws VortexbirdException {

    log.debug("updating TransactionType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("TransactionType");
    }

    validate(entity);

    if (!transactionTypeRepository.existsById(entity.getTrtyId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    return transactionTypeRepository.save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TransactionType> findById(Integer trtyId) {
    log.debug("getting TransactionType instance");
    return transactionTypeRepository.findById(trtyId);
  }
}
