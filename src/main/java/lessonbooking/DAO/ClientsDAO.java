package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

import lessonbooking.models.Client;
import lessonbooking.services.Mysqlcon;

public class ClientsDAO {
  public ClientsDAO() {
  }

  public Client fetchByUsername(String username) {
    try {
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
        return newClient;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  // create new
  public void insert(Client client) {
    try {
      if (client.getId() != -1) {
        throw new Exception("Cannot insert existing user");
      }
      Mysqlcon con = new Mysqlcon();
      con.connect();
      String queryString = String.format(
          "INSERT INTO clients (username, firstname, lastname, phone_number, password, date_of_birth) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
          client.getUsername(), client.getFirstname(), client.getLastname(), client.getPhoneNumber(),
          client.getPassword(),
          client.getDateOfBirth().toString());
      con.executeUpdate(queryString);
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // update
  public void save(Client client) {
    try {
      if (client.getId() == -1) {
        throw new Exception("Cannot update unexisting user");
      }
      Mysqlcon con = new Mysqlcon();
      con.connect();
      String queryString = String.format(
          "UPDATE clients SET username = '%s', firstname = '%s', lastname = '%s', phone_number = '%s', password = '%s', date_of_birth = '%s' WHERE id = %d",
          client.getUsername(), client.getFirstname(), client.getLastname(), client.getPhoneNumber(),
          client.getPassword(),
          client.getDateOfBirth().toString(), client.getId());
      con.executeUpdate(queryString);
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // delete
  public void delete(int clientId) {
    try {
      Mysqlcon con = new Mysqlcon();
      con.connect();
      String queryString = String.format("DELETE FROM clients WHERE id = %d", clientId);
      con.executeUpdate(queryString);
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}