package com.vobi.demo.vault.domain;

import java.io.Serial;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements java.io.Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "tran_id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tranId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "acco_id")
  @NotNull
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trty_id")
  @NotNull
  private TransactionType transactionType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_email")
  @NotNull
  private Users users;

  @NotNull
  @Column(name = "amount", nullable = false)
  private Double amount;

  @NotNull
  @Column(name = "date", nullable = false)
  private Date date;
}
