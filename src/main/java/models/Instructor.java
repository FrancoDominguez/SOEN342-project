package models;

import java.util.ArrayList;

public class Instructor extends Account {
  private String specialization;
  private String city;
  private ArrayList<Offering> offerings;

  public Instructor(String firstname, String lastname, String phoneNumber, String password, String specialization,
      String city) {
    super(firstname, lastname, phoneNumber, password);
    this.specialization = specialization;
    this.city = city;
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