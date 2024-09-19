package com.vobi.demo.vault.exception;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
public class SystemException extends VortexbirdException {

  private static final long serialVersionUID = 1L;

  public SystemException(String exception) {
    super(500, exception, null);
  }

  public SystemException(String exception, Exception e) {
    super(500, exception, e);
  }
}
