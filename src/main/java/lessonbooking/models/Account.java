package lessonbooking.models; 

import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import lessonbooking.services.Mysqlcon;

public abstract class Account {
  protected String id;
  protected String firstname;
  protected String lastname;
  protected String phoneNumber;
  protected String password;
  protected ArrayList<Booking> bookings;

  protected Account(String firstname, String lastname, String phoneNumber, String password) {
    this.id = UUID.randomUUID().toString();
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.bookings = new ArrayList<Booking>();
  }

  public void register() {
    Mysqlcon con = new Mysqlcon();
    Statement stmt = null;

    try {
      con.connect();
      stmt = con.getConnection().createStatement();

      // Concatenate the query string with values directly
      String queryString = "INSERT INTO clients (id, first_name, last_name, phone_number, password) VALUES ('" 
                           + id + "', '" + firstname + "', '" + lastname + "', '" + phoneNumber + "', '" + password + "')";

      int rowsAffected = stmt.executeUpdate(queryString);
      if (rowsAffected > 0) {
        System.out.println("Account registered successfully.");
      } else {
        System.out.println("Registration failed.");
      }

    } 
    
    catch (Exception e) {
      System.out.println("Error during registration: " + e.getMessage());
    } 
    
    finally {
      try {
        if (stmt != null) stmt.close();
        con.close();
      } 
      
      catch (Exception e) {
        System.out.println("Error closing resources: " + e.getMessage());
      }

    }

  }


  public boolean isRegistered(String id){
     return (this.id.equals(id));
  }



}
