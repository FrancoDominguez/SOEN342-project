package main.java.models;

import java.util.ArrayList;

public class Instructor extends Account {
  private String specialization;
  private ArrayList<Offering> offerings;

  public Instructor(String accountId, String firstname, String lastname, String phoneNumber, String specialization) {
    super(accountId, firstname, lastname, phoneNumber);
    this.specialization = specialization;
    this.offerings = new ArrayList<>();
  }

  public String getSpecialization() {
    return this.specialization;
  }

  public ArrayList<Offering> getOfferings() {
    return this.offerings;
  }

  public void takeOffering(Offering offering) {
    try {
      offering.assignInstructor(this);
      this.offerings.add(offering);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public String toString() {
    String stringData = String.format("accountId: %s, firstname: %s, lastname: %s, phoneNumber: %s, specialization: %s",
        this.accountId,
        this.firstname, this.lastname, this.phoneNumber, specialization);
    return stringData;
  }
}