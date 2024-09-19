package com.vobi.demo.vault.exception;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
public class UserException extends VortexbirdException {

  private static final long serialVersionUID = 1L;

  public UserException(String exception) {
    super(400, exception, null);
  }

  public UserException(String exception, Exception e) {
    super(400, exception, e);
  }
}
