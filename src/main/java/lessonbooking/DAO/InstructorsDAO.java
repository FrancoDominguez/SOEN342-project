package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

import lessonbooking.models.Instructor;
import lessonbooking.services.Mysqlcon;

public class InstructorsDAO {
  public InstructorsDAO() {
  }

  public Instructor fetchByUsername(String username) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("SELECT * FROM instructors WHERE username = %s", username);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    Instructor newInstructor;
    if (rs.next()) {
      int id = rs.getInt("id");
      String firstname = rs.getString("firstname");
      String lastname = rs.getString("lastname");
      String phoneNumber = rs.getString("phone_number");
      String password = rs.getString("password");
      LocalDate dateOfBirth = rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
      String specialization = rs.getString("specialization");
      newInstructor = new Instructor(id, username, firstname, lastname, phoneNumber, password, dateOfBirth,
          specialization);
      return newInstructor;
    }
    return null;
  }

  // create new
  public void insert(Instructor instructor) throws Exception {
    if (instructor.getId() != -1) {
      throw new Exception("Cannot insert existing user");
    }
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
    if (instructor.getId() == -1) {
      throw new Exception("Cannot update unexisting user");
    }
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