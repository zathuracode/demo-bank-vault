package com.vobi.demo.vault.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
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
public class TransactionDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @NotNull private Double amount;
  @NotNull private Date date;
  @NotNull private Integer tranId;
  private String accoIdAccount;
  private Integer trtyIdTransactionType;
  private String userEmailUsers;
}
