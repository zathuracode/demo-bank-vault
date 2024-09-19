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
import com.vobi.demo.vault.domain.Customer;
import com.vobi.demo.vault.domain.RegisteredAccount;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.exception.ZMessManager;
import com.vobi.demo.vault.repository.CustomerRepository;
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
class CustomerServiceImpl implements CustomerService {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Customer customer) throws ConstraintViolationException {

    Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {
    return customerRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Customer> findAll() {
    log.debug("finding all Customer instances");
    return customerRepository.findAll();
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public Customer save(Customer entity) throws VortexbirdException {
    log.debug("saving Customer instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Customer");
    }

    validate(entity);

    return customerRepository.save(entity);
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void delete(Customer entity) throws VortexbirdException {
    log.debug("deleting Customer instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Customer");
    }

    if (entity.getCustId() == null) {
      throw new ZMessManager().new EmptyFieldException("custId");
    }

    if (!customerRepository.existsById(entity.getCustId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    findById(entity.getCustId())
        .ifPresent(
            entidad -> {
              List<Account> accounts = entidad.getAccounts();
              if (Utilities.validationsList(accounts)) {
                throw new ZMessManager().new DeletingException("accounts");
              }
              List<RegisteredAccount> registeredAccounts = entidad.getRegisteredAccounts();
              if (Utilities.validationsList(registeredAccounts)) {
                throw new ZMessManager().new DeletingException("registeredAccounts");
              }
            });

    customerRepository.deleteById(entity.getCustId());
    log.debug("delete Customer successful");
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void deleteById(Integer id) throws VortexbirdException {
    log.debug("deleting Customer instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("custId");
    }
    Optional<Customer> optionalCustomer = customerRepository.findById(id);
    if (optionalCustomer.isPresent()) {
      delete(optionalCustomer.get());
    }
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public Customer update(Customer entity) throws VortexbirdException {

    log.debug("updating Customer instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Customer");
    }

    validate(entity);

    if (!customerRepository.existsById(entity.getCustId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    return customerRepository.save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Customer> findById(Integer custId) {
    log.debug("getting Customer instance");
    return customerRepository.findById(custId);
  }
}
