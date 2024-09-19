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

import com.vobi.demo.vault.domain.UserType;
import com.vobi.demo.vault.dto.UserTypeDTO;
import com.vobi.demo.vault.entity.service.UserTypeService;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.mapper.UserTypeMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@RestController
@RequestMapping("/api/v1/userType")
@Slf4j
public class UserTypeRestController {

  @Autowired private UserTypeService userTypeService;

  @Autowired private UserTypeMapper userTypeMapper;

  @GetMapping(value = "/{ustyId}")
  public ResponseEntity<?> findById(@PathVariable("ustyId") Integer ustyId)
      throws VortexbirdException {
    log.debug("Request to findById() UserType");

    Optional<UserType> optionalUserType = userTypeService.findById(ustyId);

    UserType userType = optionalUserType.isPresent() ? optionalUserType.get() : null;

    return ResponseEntity.ok().body(userTypeMapper.userTypeToUserTypeDTO(userType));
  }

  @GetMapping()
  public ResponseEntity<?> findAll() throws VortexbirdException {
    log.debug("Request to findAll() UserType");

    return ResponseEntity.ok()
        .body(userTypeMapper.listUserTypeToListUserTypeDTO(userTypeService.findAll()));
  }

  @PostMapping()
  public ResponseEntity<?> save(@Valid @RequestBody UserTypeDTO userTypeDTO)
      throws VortexbirdException {
    log.debug("Request to save UserType: {}", userTypeDTO);

    UserType userType = userTypeMapper.userTypeDTOToUserType(userTypeDTO);
    userType = userTypeService.save(userType);
    return ResponseEntity.ok().body(userTypeMapper.userTypeToUserTypeDTO(userType));
  }

  @PutMapping()
  public ResponseEntity<?> update(@Valid @RequestBody UserTypeDTO userTypeDTO)
      throws VortexbirdException {
    log.debug("Request to update UserType: {}", userTypeDTO);

    UserType userType = userTypeMapper.userTypeDTOToUserType(userTypeDTO);
    userType = userTypeService.update(userType);

    return ResponseEntity.ok().body(userTypeMapper.userTypeToUserTypeDTO(userType));
  }

  @DeleteMapping(value = "/{ustyId}")
  public ResponseEntity<?> delete(@PathVariable("ustyId") Integer ustyId)
      throws VortexbirdException {
    log.debug("Request to delete UserType");

    userTypeService.deleteById(ustyId);

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(userTypeService.count());
  }
}
