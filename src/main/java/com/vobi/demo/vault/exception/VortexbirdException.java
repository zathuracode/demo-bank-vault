package com.vobi.demo.vault.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@Getter
@Setter
public class VortexbirdException extends Exception {
  private static final long serialVersionUID = 1L;
  private final Integer code;
  private final String exception;

  public VortexbirdException(Integer code, String exception, Exception e) {
    super(e);
    this.code = code;
    this.exception = exception;
  }

  public VortexbirdException(String exception, Exception e) {
    super(e);
    this.code = null;
    this.exception = exception;
  }
}
