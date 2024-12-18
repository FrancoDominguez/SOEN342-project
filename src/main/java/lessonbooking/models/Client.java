package lessonbooking.models;

import java.time.LocalDate;

public class Client extends Account {

  public Client(String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth) {
    super(username, firstname, lastname, phoneNumber, password, dateOfBirth);
  }

  public Client(int id, String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth) {
    super(id, username, firstname, lastname, phoneNumber, password, dateOfBirth);
  }

  @Override
  public String toString() {
    return "Client Info: " +
        "Name: " + firstname + " " + lastname + ", " +
        "Username: " + username + ", " +
        "Phone: " + phoneNumber + ", " +
        "Date of Birth: " + dateOfBirth;
  }
}