package lessonbooking.models;

import java.util.ArrayList;
import java.util.UUID;
import lessonbooking.services.Mysqlcon;

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

    // Possible implementation where registeredAccounts would be in the DB
    // Check if the account is already registered

    /*
     * for (Account account : registeredAccounts) {
     * if (account.phoneNumber.equals(this.phoneNumber)) {
     * System.out.println("Account with this phone number already exists.");
     * return;
     * }
     * }
     * 
     * // Add the new account to the list
     * registeredAccounts.add(this);
     */
  };

  public void login() {
  }

  public boolean isPasswordCorrect(String password) {
    return (this.password.equals(password));
  }
}
