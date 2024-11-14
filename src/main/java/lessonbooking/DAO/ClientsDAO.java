package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

import lessonbooking.models.Client;
import lessonbooking.services.Mysqlcon;

public class ClientsDAO {
  public ClientsDAO() {
  }

  public Client fetchByUsername(String username) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("SELECT * FROM clients WHERE username = %s", username);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    Client newClient;
    if (rs.next()) {
      int id = rs.getInt("id");
      String firstname = rs.getString("firstname");
      String lastname = rs.getString("lastname");
      String phoneNumber = rs.getString("phone_number");
      String password = rs.getString("password");
      LocalDate dateOfBirth = rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
      newClient = new Client(id, username, firstname, lastname, phoneNumber, password, dateOfBirth);
      con.close();
      return newClient;
    } else {

    }
    con.close();
    return null;
  }

  // create new
  public void insert(Client client) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "INSERT INTO clients (username, firstname, lastname, phone_number, password, date_of_birth) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
        client.getUsername(), client.getFirstname(), client.getLastname(), client.getPhoneNumber(),
        client.getPassword(),
        client.getDateOfBirth().toString());
    con.executeUpdate(queryString);
    con.close();
  }

  // update
  public void save(Client client) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "UPDATE clients SET username = '%s', firstname = '%s', lastname = '%s', phone_number = '%s', password = '%s', date_of_birth = '%s' WHERE id = %d",
        client.getUsername(), client.getFirstname(), client.getLastname(), client.getPhoneNumber(),
        client.getPassword(),
        client.getDateOfBirth().toString(), client.getId());
    con.executeUpdate(queryString);
    con.close();
  }

  // delete
  public void delete(int clientId) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("DELETE FROM clients WHERE id = %d", clientId);
    con.executeUpdate(queryString);
    con.close();
  }
}