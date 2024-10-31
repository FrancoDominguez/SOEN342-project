package main.java.models;

public class Instructor extends Account {
  private String accountId;
  private String firstname;
  private String lastname;
  private String phoneNumber;
  private String specialization;

  public Instructor(String accountId, String firstname, String lastname, String phoneNumber, String specialization) {
    super(accountId, firstname, lastname, phoneNumber);
    this.specialization = specialization;
  }

  public Offering[] viewOfferings() {
    // implement viewOfferings() function
    return null;
  };

  public boolean takeOffering(Offering offering) {
    return false;
  };

  public String toString() {
    String stringData = String.format("accountId: %s, firstname: %s, lastname: %s, phoneNumber: %s, specialization: %s",
        accountId,
        firstname, lastname, phoneNumber, specialization);
    return stringData;
  }
}
