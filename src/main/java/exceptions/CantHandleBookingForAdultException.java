package main.java.exceptions;

public class CantHandleBookingForAdultException extends Exception {
  public CantHandleBookingForAdultException(String message) {
    super(message);
  }
}