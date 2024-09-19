package com.vobi.demo.vault.entity.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.demo.vault.domain.Account;
import com.vobi.demo.vault.domain.RegisteredAccount;
import com.vobi.demo.vault.domain.Transaction;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.exception.ZMessManager;
import com.vobi.demo.vault.repository.AccountRepository;
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
class AccountServiceImpl implements AccountService {

  @Autowired private AccountRepository accountRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Account account) throws ConstraintViolationException {

    Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {
    return accountRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Account> findAll() {
    log.debug("finding all Account instances");
    return accountRepository.findAll();
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public Account save(Account entity) throws VortexbirdException {
    log.debug("saving Account instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Account");
    }

    validate(entity);

    return accountRepository.save(entity);
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void delete(Account entity) throws VortexbirdException {
    log.debug("deleting Account instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Account");
    }

    if (entity.getAccoId() == null) {
      throw new ZMessManager().new EmptyFieldException("accoId");
    }

    if (!accountRepository.existsById(entity.getAccoId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    findById(entity.getAccoId())
        .ifPresent(
            entidad -> {
              List<RegisteredAccount> registeredAccounts = entidad.getRegisteredAccounts();
              if (Utilities.validationsList(registeredAccounts)) {
                throw new ZMessManager().new DeletingException("registeredAccounts");
              }
              List<Transaction> transactions = entidad.getTransactions();
              if (Utilities.validationsList(transactions)) {
                throw new ZMessManager().new DeletingException("transactions");
              }
            });

    accountRepository.deleteById(entity.getAccoId());
    log.debug("delete Account successful");
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void deleteById(String id) throws VortexbirdException {
    log.debug("deleting Account instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("accoId");
    }
    Optional<Account> optionalAccount = accountRepository.findById(id);
    if (optionalAccount.isPresent()) {
      delete(optionalAccount.get());
    }
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public Account update(Account entity) throws VortexbirdException {

    log.debug("updating Account instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Account");
    }

    validate(entity);

    if (!accountRepository.existsById(entity.getAccoId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    return accountRepository.save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Account> findById(String accoId) {
    log.debug("getting Account instance");
    return accountRepository.findById(accoId);
  }
}
