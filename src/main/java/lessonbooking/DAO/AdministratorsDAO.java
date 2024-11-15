package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

import lessonbooking.models.Administrator;
import lessonbooking.services.Mysqlcon;

public class AdministratorsDAO {
  public AdministratorsDAO() {
  }

  public Administrator fetchByUsername(String username) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("SELECT * FROM administrators WHERE username = '%s'", username);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    Administrator newAdministrator;
    if (rs.next()) {
      int id = rs.getInt("id");
      String firstname = rs.getString("firstname");
      String lastname = rs.getString("lastname");
      String phoneNumber = rs.getString("phone_number");
      String password = rs.getString("password");
      LocalDate dateOfBirth = rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
      newAdministrator = new Administrator(id, username, firstname, lastname, phoneNumber, password, dateOfBirth);
      return newAdministrator;
    }
    return null;
  }

  // create new
  public void insert(Administrator administrator) throws Exception {
    if (administrator.getId() != -1) {
      throw new Exception("Cannot insert existing user");
    }
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "INSERT INTO administrators (username, firstname, lastname, phone_number, password, date_of_birth) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
        administrator.getUsername(), administrator.getFirstname(), administrator.getLastname(),
        administrator.getPhoneNumber(),
        administrator.getPassword(),
        administrator.getDateOfBirth().toString());
    con.executeUpdate(queryString);
    con.close();
  }

  // update
  public void save(Administrator administrator) throws Exception {
    if (administrator.getId() == -1) {
      throw new Exception("Cannot update unexisting user");
    }
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "UPDATE administrators SET username = '%s', firstname = '%s', lastname = '%s', phone_number = '%s', password = '%s', date_of_birth = '%s' WHERE id = %d",
        administrator.getUsername(), administrator.getFirstname(), administrator.getLastname(),
        administrator.getPhoneNumber(),
        administrator.getPassword(),
        administrator.getDateOfBirth().toString(), administrator.getId());
    con.executeUpdate(queryString);
    con.close();
  }

  // delete
  public void delete(int administratorId) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("DELETE FROM administrators WHERE id = %d", administratorId);
    con.executeUpdate(queryString);
    con.close();
  }
}