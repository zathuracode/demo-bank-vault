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

import com.vobi.demo.vault.domain.Users;
import com.vobi.demo.vault.dto.UsersDTO;
import com.vobi.demo.vault.entity.service.UsersService;
import com.vobi.demo.vault.exception.VortexbirdException;
import com.vobi.demo.vault.mapper.UsersMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UsersRestController {

  @Autowired private UsersService usersService;

  @Autowired private UsersMapper usersMapper;

  @GetMapping(value = "/{userEmail}")
  public ResponseEntity<?> findById(@PathVariable("userEmail") String userEmail)
      throws VortexbirdException {
    log.debug("Request to findById() Users");

    Optional<Users> optionalUsers = usersService.findById(userEmail);

    Users users = optionalUsers.isPresent() ? optionalUsers.get() : null;

    return ResponseEntity.ok().body(usersMapper.usersToUsersDTO(users));
  }

  @GetMapping()
  public ResponseEntity<?> findAll() throws VortexbirdException {
    log.debug("Request to findAll() Users");

    return ResponseEntity.ok().body(usersMapper.listUsersToListUsersDTO(usersService.findAll()));
  }

  @PostMapping()
  public ResponseEntity<?> save(@Valid @RequestBody UsersDTO usersDTO) throws VortexbirdException {
    log.debug("Request to save Users: {}", usersDTO);

    Users users = usersMapper.usersDTOToUsers(usersDTO);
    users = usersService.save(users);
    return ResponseEntity.ok().body(usersMapper.usersToUsersDTO(users));
  }

  @PutMapping()
  public ResponseEntity<?> update(@Valid @RequestBody UsersDTO usersDTO)
      throws VortexbirdException {
    log.debug("Request to update Users: {}", usersDTO);

    Users users = usersMapper.usersDTOToUsers(usersDTO);
    users = usersService.update(users);

    return ResponseEntity.ok().body(usersMapper.usersToUsersDTO(users));
  }

  @DeleteMapping(value = "/{userEmail}")
  public ResponseEntity<?> delete(@PathVariable("userEmail") String userEmail)
      throws VortexbirdException {
    log.debug("Request to delete Users");

    usersService.deleteById(userEmail);

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(usersService.count());
  }
}
