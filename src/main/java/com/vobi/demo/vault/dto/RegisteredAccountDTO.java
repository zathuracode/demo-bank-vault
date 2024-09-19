package com.vobi.demo.vault.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredAccountDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @NotNull
  @NotEmpty
  @Size(max = 1)
  private String enable;

  @NotNull private Integer reacId;
  private String accoIdAccount;
  private Integer custIdCustomer;
}
