package com.vobi.demo.vault.entity.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.demo.vault.domain.UserType;
import com.vobi.demo.vault.domain.Users;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.exception.ZMessManager;
import com.vobi.demo.vault.repository.UserTypeRepository;
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
class UserTypeServiceImpl implements UserTypeService {

  @Autowired private UserTypeRepository userTypeRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(UserType userType) throws ConstraintViolationException {

    Set<ConstraintViolation<UserType>> constraintViolations = validator.validate(userType);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {
    return userTypeRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserType> findAll() {
    log.debug("finding all UserType instances");
    return userTypeRepository.findAll();
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public UserType save(UserType entity) throws VortexbirdException {
    log.debug("saving UserType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("UserType");
    }

    validate(entity);

    return userTypeRepository.save(entity);
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void delete(UserType entity) throws VortexbirdException {
    log.debug("deleting UserType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("UserType");
    }

    if (entity.getUstyId() == null) {
      throw new ZMessManager().new EmptyFieldException("ustyId");
    }

    if (!userTypeRepository.existsById(entity.getUstyId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    findById(entity.getUstyId())
        .ifPresent(
            entidad -> {
              List<Users> userses = entidad.getUserses();
              if (Utilities.validationsList(userses)) {
                throw new ZMessManager().new DeletingException("userses");
              }
            });

    userTypeRepository.deleteById(entity.getUstyId());
    log.debug("delete UserType successful");
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public void deleteById(Integer id) throws VortexbirdException {
    log.debug("deleting UserType instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("ustyId");
    }
    Optional<UserType> optionalUserType = userTypeRepository.findById(id);
    if (optionalUserType.isPresent()) {
      delete(optionalUserType.get());
    }
  }

  @Override
  @Transactional(
      readOnly = false,
      propagation = Propagation.REQUIRED,
      rollbackFor = Exception.class)
  public UserType update(UserType entity) throws VortexbirdException {

    log.debug("updating UserType instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("UserType");
    }

    validate(entity);

    if (!userTypeRepository.existsById(entity.getUstyId())) {
      throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
    }

    return userTypeRepository.save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserType> findById(Integer ustyId) {
    log.debug("getting UserType instance");
    return userTypeRepository.findById(ustyId);
  }
}
