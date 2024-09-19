package com.vobi.demo.vault.domain;

import java.io.Serial;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "registered_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredAccount implements java.io.Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "reac_id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer reacId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "acco_id")
  @NotNull
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cust_id")
  @NotNull
  private Customer customer;

  @NotNull
  @NotEmpty
  @Size(max = 1)
  @Column(name = "enable", nullable = false)
  private String enable;
}
