package exceptions;

public class CantHandleBookingForAdultException extends Exception {
  public CantHandleBookingForAdultException(String message) {
    super(message);
  }
}