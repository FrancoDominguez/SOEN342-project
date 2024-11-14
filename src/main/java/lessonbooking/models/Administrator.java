package lessonbooking.models;

import java.time.LocalDate;

public class Administrator extends Account {
  public Administrator(String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth) {
    super(username, firstname, lastname, phoneNumber, password, dateOfBirth);
  }

  public Administrator(int id, String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth) {
    super(id, username, firstname, lastname, phoneNumber, password, dateOfBirth);
  }
}
