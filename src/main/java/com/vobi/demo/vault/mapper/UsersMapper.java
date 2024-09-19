package com.vobi.demo.vault.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.demo.vault.domain.Users;
import com.vobi.demo.vault.dto.UsersDTO;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface UsersMapper {

  @Mapping(source = "userType.ustyId", target = "ustyIdUserType")
  public UsersDTO usersToUsersDTO(Users users);

  @Mapping(source = "ustyIdUserType", target = "userType.ustyId")
  public Users usersDTOToUsers(UsersDTO usersDTO);

  public List<UsersDTO> listUsersToListUsersDTO(List<Users> userss);

  public List<Users> listUsersDTOToListUsers(List<UsersDTO> usersDTOs);
}
