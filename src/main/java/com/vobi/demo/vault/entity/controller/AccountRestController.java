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

import com.vobi.demo.vault.domain.Account;
import com.vobi.demo.vault.dto.AccountDTO;
import com.vobi.demo.vault.entity.service.AccountService;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.mapper.AccountMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@RestController
@RequestMapping("/api/v1/account")
@Slf4j
public class AccountRestController {

  @Autowired private AccountService accountService;

  @Autowired private AccountMapper accountMapper;

  @GetMapping(value = "/{accoId}")
  public ResponseEntity<?> findById(@PathVariable("accoId") String accoId)
      throws VortexbirdException {
    log.debug("Request to findById() Account");

    Optional<Account> optionalAccount = accountService.findById(accoId);

    Account account = optionalAccount.isPresent() ? optionalAccount.get() : null;

    return ResponseEntity.ok().body(accountMapper.accountToAccountDTO(account));
  }

  @GetMapping()
  public ResponseEntity<?> findAll() throws VortexbirdException {
    log.debug("Request to findAll() Account");

    return ResponseEntity.ok()
        .body(accountMapper.listAccountToListAccountDTO(accountService.findAll()));
  }

  @PostMapping()
  public ResponseEntity<?> save(@Valid @RequestBody AccountDTO accountDTO)
      throws VortexbirdException {
    log.debug("Request to save Account: {}", accountDTO);

    Account account = accountMapper.accountDTOToAccount(accountDTO);
    account = accountService.save(account);
    return ResponseEntity.ok().body(accountMapper.accountToAccountDTO(account));
  }

  @PutMapping()
  public ResponseEntity<?> update(@Valid @RequestBody AccountDTO accountDTO)
      throws VortexbirdException {
    log.debug("Request to update Account: {}", accountDTO);

    Account account = accountMapper.accountDTOToAccount(accountDTO);
    account = accountService.update(account);

    return ResponseEntity.ok().body(accountMapper.accountToAccountDTO(account));
  }

  @DeleteMapping(value = "/{accoId}")
  public ResponseEntity<?> delete(@PathVariable("accoId") String accoId)
      throws VortexbirdException {
    log.debug("Request to delete Account");

    accountService.deleteById(accoId);

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(accountService.count());
  }
}
