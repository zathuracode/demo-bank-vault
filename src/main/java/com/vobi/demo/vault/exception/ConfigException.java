package com.vobi.demo.vault.exception;

public class ConfigException extends VortexbirdException {

  /**
   * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
   * @generationDate 2024-09-19T17:02:34.813843
   */
  private static final long serialVersionUID = 1L;

  public ConfigException(String exception) {
    super(412, exception, null);
  }

  public ConfigException(String exception, Exception e) {
    super(412, exception, e);
  }
}
