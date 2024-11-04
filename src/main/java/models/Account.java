package models;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Account {
  protected String accountId;
  protected String firstname;
  protected String lastname;
  protected String phoneNumber;
  protected String password;
  protected ArrayList<Booking> bookings;

  protected Account(String firstname, String lastname, String phoneNumber, String password) {
    this.accountId = UUID.randomUUID().toString();
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.bookings = new ArrayList<Booking>();
  }

  public void register() {
    // this will add the new account object to the list of accounts
    // whether that list is in the db or elsewhere
  };

  public void login() {
  }

  public boolean isPasswordCorrect(String password) {
    if (this.password.equals(password)) {
      return true;
    } else {
      return false;
    }
  };
}