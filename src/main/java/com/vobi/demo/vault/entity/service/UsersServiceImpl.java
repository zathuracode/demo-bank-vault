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
import com.vobi.demo.vault.domain.Users;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.exception.ZMessManager;
import com.vobi.demo.vault.repository.UsersRepository;
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
class UsersServiceImpl implements UsersService {

  @Autowired private UsersRepository usersRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Users users) throws ConstraintViolationException {

    Set<ConstraintViolation<Users>> constraintViolations = validator.validate(users);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {
    return usersRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Users> findAll() {
    log.debug("finding all Users instances");
    return usersRepository.findAll();
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public Users save(Users entity) throws VortexbirdException {
    log.debug("saving Users instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Users");
    }

    validate(entity);

    return usersRepository.save(entity);
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void delete(Users entity) throws VortexbirdException {
    log.debug("deleting Users instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Users");
    }

    if (entity.getUserEmail() == null) {
      throw new ZMessManager().new EmptyFieldException("userEmail");
    }

    if (!usersRepository.existsById(entity.getUserEmail())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    findById(entity.getUserEmail())
        .ifPresent(
            entidad -> {
              List<Transaction> transactions = entidad.getTransactions();
              if (Utilities.validationsList(transactions)) {
                throw new ZMessManager().new DeletingException("transactions");
              }
            });

    usersRepository.deleteById(entity.getUserEmail());
    log.debug("delete Users successful");
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void deleteById(String id) throws VortexbirdException {
    log.debug("deleting Users instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("userEmail");
    }
    Optional<Users> optionalUsers = usersRepository.findById(id);
    if (optionalUsers.isPresent()) {
      delete(optionalUsers.get());
    }
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public Users update(Users entity) throws VortexbirdException {

    log.debug("updating Users instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Users");
    }

    validate(entity);

    if (!usersRepository.existsById(entity.getUserEmail())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    return usersRepository.save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Users> findById(String userEmail) {
    log.debug("getting Users instance");
    return usersRepository.findById(userEmail);
  }
}
