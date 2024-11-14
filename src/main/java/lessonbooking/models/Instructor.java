package lessonbooking.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Instructor extends Account {
  private String specialization;
  private ArrayList<String> cities;
  private ArrayList<Offering> offerings;

  // new account
  public Instructor(String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth,
      String specialization) {
    super(username, firstname, lastname, phoneNumber, password, dateOfBirth);
    this.specialization = specialization;
    this.cities = new ArrayList<String>();
    this.offerings = new ArrayList<Offering>();
  }

  // account from db
  public Instructor(int id, String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth, String specialization) {
    super(id, username, firstname, lastname, phoneNumber, password, dateOfBirth);
    this.specialization = specialization;
    this.cities = new ArrayList<String>();
    this.offerings = new ArrayList<Offering>();
  }

  public String getSpecialization() {
    return this.specialization;
  }

  public ArrayList<Offering> getOfferings() {
    return this.offerings;
  }

  public ArrayList<String> getCities() {
    return this.cities;
  }

}