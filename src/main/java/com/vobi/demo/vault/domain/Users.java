package com.vobi.demo.vault.domain;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements java.io.Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "user_email", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String userEmail;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usty_id")
  @NotNull
  private UserType userType;

  @NotNull
  @NotEmpty
  @Size(max = 1)
  @Column(name = "enable", nullable = false)
  private String enable;

  @NotNull
  @NotEmpty
  @Size(max = 255)
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "token")
  private String token;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
  private List<Transaction> transactions = new ArrayList<>();
}
