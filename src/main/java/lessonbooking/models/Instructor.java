package lessonbooking.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Instructor extends Account {
  private String specialization;
  private ArrayList<String> cities;

  // new account
  public Instructor(String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth, String specialization, ArrayList<String> cities) {
    super(username, firstname, lastname, phoneNumber, password, dateOfBirth);
    this.specialization = specialization;
    this.cities = cities;
  }

  // account from db
  public Instructor(int id, String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth, String specialization, ArrayList<String> cities) {
    super(id, username, firstname, lastname, phoneNumber, password, dateOfBirth);
    this.specialization = specialization;
    this.cities = cities;
  }

  public String getSpecialization() {
    return this.specialization;
  }

  public ArrayList<String> getCities() {
    return this.cities;
  }

  public Boolean hasCity(String city) {
    return this.cities.contains(city);
  }

  public Boolean isSpecializedIn(String specialization) {
    return (this.specialization.equals(specialization));
  }
}