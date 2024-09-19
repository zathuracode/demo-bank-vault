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

import com.vobi.demo.vault.domain.RegisteredAccount;
import com.vobi.demo.vault.dto.RegisteredAccountDTO;
import com.vobi.demo.vault.entity.service.RegisteredAccountService;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.mapper.RegisteredAccountMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@RestController
@RequestMapping("/api/v1/registeredAccount")
@Slf4j
public class RegisteredAccountRestController {

  @Autowired private RegisteredAccountService registeredAccountService;

  @Autowired private RegisteredAccountMapper registeredAccountMapper;

  @GetMapping(value = "/{reacId}")
  public ResponseEntity<?> findById(@PathVariable("reacId") Integer reacId)
      throws VortexbirdException {
    log.debug("Request to findById() RegisteredAccount");

    Optional<RegisteredAccount> optionalRegisteredAccount =
        registeredAccountService.findById(reacId);

    RegisteredAccount registeredAccount =
        optionalRegisteredAccount.isPresent() ? optionalRegisteredAccount.get() : null;

    return ResponseEntity.ok()
        .body(registeredAccountMapper.registeredAccountToRegisteredAccountDTO(registeredAccount));
  }

  @GetMapping()
  public ResponseEntity<?> findAll() throws VortexbirdException {
    log.debug("Request to findAll() RegisteredAccount");

    return ResponseEntity.ok()
        .body(
            registeredAccountMapper.listRegisteredAccountToListRegisteredAccountDTO(
                registeredAccountService.findAll()));
  }

  @PostMapping()
  public ResponseEntity<?> save(@Valid @RequestBody RegisteredAccountDTO registeredAccountDTO)
      throws VortexbirdException {
    log.debug("Request to save RegisteredAccount: {}", registeredAccountDTO);

    RegisteredAccount registeredAccount =
        registeredAccountMapper.registeredAccountDTOToRegisteredAccount(registeredAccountDTO);
    registeredAccount = registeredAccountService.save(registeredAccount);
    return ResponseEntity.ok()
        .body(registeredAccountMapper.registeredAccountToRegisteredAccountDTO(registeredAccount));
  }

  @PutMapping()
  public ResponseEntity<?> update(@Valid @RequestBody RegisteredAccountDTO registeredAccountDTO)
      throws VortexbirdException {
    log.debug("Request to update RegisteredAccount: {}", registeredAccountDTO);

    RegisteredAccount registeredAccount =
        registeredAccountMapper.registeredAccountDTOToRegisteredAccount(registeredAccountDTO);
    registeredAccount = registeredAccountService.update(registeredAccount);

    return ResponseEntity.ok()
        .body(registeredAccountMapper.registeredAccountToRegisteredAccountDTO(registeredAccount));
  }

  @DeleteMapping(value = "/{reacId}")
  public ResponseEntity<?> delete(@PathVariable("reacId") Integer reacId)
      throws VortexbirdException {
    log.debug("Request to delete RegisteredAccount");

    registeredAccountService.deleteById(reacId);

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(registeredAccountService.count());
  }
}
