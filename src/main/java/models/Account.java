package main.java.models;

public abstract class Account {
  private String accountId;
  private String firstname;
  private String lastname;
  private String phoneNumber;

  public Account(String accountId, String firstname, String lastname, String phoneNumber) {
    this.accountId = accountId;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
  }

  public boolean register() {
    return false;
  };

  public boolean login() {
    // implement login function in here
    return false;
  };

  public String toString() {
    String stringData = String.format("accountId: %s, firstname: %s, lastname: %s, phoneNumber: %s", accountId,
        firstname, lastname, phoneNumber);
    return stringData;
  }
}