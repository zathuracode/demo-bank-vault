package com.vobi.demo.vault.exception;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
public class ZMessManager extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public static final String ALL = "All ";
  public static final String ENTCHILD = "related tables(childs)";
  public static final String FOREIGNDATA = "foreign classes data: ";
  public static final String ENTITY_SUCCESFULLYSAVED = "Entity succesfully saved";
  public static final String ENTITY_SUCCESFULLYDELETED = "Entity succesfully deleted";
  public static final String ENTITY_SUCCESFULLYMODIFIED = "Entity succesfully modified";
  public static final String ENTITY_WITHSAMEKEY = "Another Entity with the same key was found";
  public static final String ENTITY_NOENTITYTOUPDATE = "No Entity was found, with the typed key ";

  public ZMessManager() {}

  public ZMessManager(String exception) {
    super(exception);
  }

  public class NotValidFieldException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public NotValidFieldException(String info) {
      super("The value for the field: \"" + info + "\" is not valid");
    }
  }

  public class NullEntityExcepcion extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public NullEntityExcepcion(String info) {
      super("The " + info + " Entity can not be null or empty");
    }
  }

  public class EmptyFieldException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public EmptyFieldException(String info) {
      super("The value for the field: \"" + info + "\" can not be null or empty");
    }
  }

  public class NotValidFormatException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public NotValidFormatException(String info) {
      super("The Format or length for the field: \"" + info + "\" is not valid");
    }
  }

  public class DeletingException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public DeletingException(String info) {
      super(
          "The Entity you are trying to delete "
              + "may have related information, "
              + "please before trying again, "
              + "check the data on the entity, \""
              + info
              + "\"");
    }
  }

  public class ForeignException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public ForeignException(String info) {
      super("There was no data related with the input \"" + info + "\"");
    }
  }

  public class GettingException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public GettingException(String info) {
      super("There was an exception getting " + info);
    }
  }

  public class FindingException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public FindingException(String info) {
      super("There was an exception trying to find " + info);
    }
  }
}
