package lessonbooking.models; 

import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import lessonbooking.services.Mysqlcon;

public abstract class Account {
  protected int id;
  protected String firstname;
  protected String lastname;
  protected String phoneNumber;
  protected String password;
  protected LocalDate dateOfBirth;
  protected ArrayList<Booking> bookings;

  protected Account(String firstname, String lastname, String phoneNumber, String password, LocalDate dateofBirthInput) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.dateOfBirth = dateofBirthInput;
    this.bookings = new ArrayList<Booking>();
  }

  public void register() {
    Mysqlcon con = new Mysqlcon();
    Statement stmt = null;

    try {
      con.connect();
      stmt = con.getConnection().createStatement();

      // Concatenate the query string with values directly
      String queryString = "INSERT INTO clients (id, firstname, lastname, phone_number, password, date_of_birth) VALUES ('" 
                           + this.id + "', '" + this.firstname + "', '" + this.lastname + "', '" + this.phoneNumber + "', '" + this.password + "','" + this.dateOfBirth.toString() + "')";
      System.out.println(queryString);
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


  public boolean isRegistered(int id){
     return (this.id == id);
  }



}
