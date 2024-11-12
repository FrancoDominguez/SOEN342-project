package lessonbooking.models;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import lessonbooking.services.Mysqlcon;

public class Instructor extends Account {
  private String specialization;
  //private ArrayList<String> cities;
  private ArrayList<Offering> offerings;

  public Instructor(String firstname, String lastname, String phoneNumber, String password, LocalDate dateOfBirth, String specialization){
    super(firstname, lastname, phoneNumber, password, dateOfBirth);
    this.specialization = specialization;
    //this.cities = cities;
  }

  public void register() {
    Mysqlcon con = new Mysqlcon();
    Statement stmt = null;

    try {
      con.connect();
      stmt = con.getConnection().createStatement();

      // Concatenate the query string with values directly
      String queryString = "INSERT INTO instructors (id, firstname, lastname, phone_number, password, date_of_birth) VALUES ('" 
                            + this.id + "', '" + this.firstname + "', '" + this.lastname + "', '" + this.phoneNumber + "', '" + this.password + "','" + this.dateOfBirth.toString() + "', '" + this.specialization + "')";
      //System.out.println(queryString); to check if query values are good
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


    public String getSpecialization() {
      return this.specialization;
    }

    public ArrayList<Offering> getOfferings() {
      return this.offerings;
    }

    public void takeOffering(Offering offering) {
      try {
        offering.assignInstructor(this);
        this.offerings.add(offering);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

  public String toString() {
    String stringData = String.format("id: %s, firstname: %s, lastname: %s, phoneNumber: %s, specialization: %s",
        this.id,
        this.firstname, this.lastname, this.phoneNumber, specialization);
    return stringData;
  }
}