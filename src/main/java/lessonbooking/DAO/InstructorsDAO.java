package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import lessonbooking.models.Instructor;
import lessonbooking.services.Mysqlcon;

public class InstructorsDAO {
  public InstructorsDAO() {
  }

  public Instructor fetchByUsername(String username) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();

    String queryString = String.format(
        "SELECT * FROM instructors WHERE username = '%s'", username);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();

    Instructor instructor = null;

    if (rs.next()) {
      int id = rs.getInt("id");
      String firstname = rs.getString("firstname");
      String lastname = rs.getString("lastname");
      String phoneNumber = rs.getString("phone_number");
      String password = rs.getString("password");
      LocalDate dateOfBirth = rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
      String specialization = rs.getString("specialization");

      String citiesQuery = String.format(
          "SELECT city FROM instructor_cities WHERE instructor_id = %d", id);
      con.executeQuery(citiesQuery);
      ResultSet citiesRs = con.getResultSet();

      ArrayList<String> cities = new ArrayList<>();
      while (citiesRs.next()) {
        cities.add(citiesRs.getString("city"));
      }

      instructor = new Instructor(id, username, firstname, lastname, phoneNumber, password,
          dateOfBirth, specialization, cities);
    }

    con.close();
    return instructor;
  }

  // create new
  public void insert(Instructor instructor) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "INSERT INTO instructors (username, firstname, lastname, phone_number, password, date_of_birth, specialization) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
        instructor.getUsername(), instructor.getFirstname(), instructor.getLastname(), instructor.getPhoneNumber(),
        instructor.getPassword(),
        instructor.getDateOfBirth().toString());

    con.executeUpdate(queryString);
    con.close();
  }

  // update
  public void save(Instructor instructor) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "UPDATE instructors SET username = '%s', firstname = '%s', lastname = '%s', phone_number = '%s', password = '%s', date_of_birth = '%s', specialization = '%s' WHERE id = %d",
        instructor.getUsername(), instructor.getFirstname(), instructor.getLastname(), instructor.getPhoneNumber(),
        instructor.getPassword(),
        instructor.getDateOfBirth().toString(), instructor.getId(), instructor.getSpecialization());
    con.executeUpdate(queryString);
    con.close();
  }

  // delete
  public void delete(int instructorId) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("DELETE FROM instructors WHERE id = %d", instructorId);
    con.executeUpdate(queryString);
    con.close();
  }
}