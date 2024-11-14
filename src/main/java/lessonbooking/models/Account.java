package lessonbooking.models;

import java.time.LocalDate;
import java.time.Period;

public abstract class Account {
  protected int id;
  protected String username;
  protected String firstname;
  protected String lastname;
  protected String phoneNumber;
  protected String password;
  protected LocalDate dateOfBirth;

  // new account
  protected Account(String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateofBirthInput) {
    this.id = -1;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.dateOfBirth = dateofBirthInput;
  }

  // account from db
  protected Account(int id, String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateofBirthInput) {
    this.id = id;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.dateOfBirth = dateofBirthInput;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  protected boolean isRegistered() {
    return (this.id != -1);
  }

  public Boolean isAdult() {
    LocalDate currentDate = LocalDate.now();
    Period age = Period.between(this.dateOfBirth, currentDate);
    return age.getYears() >= 18;
  }

  public Boolean validatePassword(String password) {
    return (this.password.equals(password));
  }
}