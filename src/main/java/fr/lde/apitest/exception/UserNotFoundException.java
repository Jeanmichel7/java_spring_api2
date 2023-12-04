package fr.lde.apitest.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(Long id) {
    super("User " + id + " not found");
  }

}
