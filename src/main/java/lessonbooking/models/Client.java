package lessonbooking.models;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lessonbooking.exceptions.CantHandleBookingForAdultException;
import lessonbooking.exceptions.UnderageException;
import lessonbooking.services.Mysqlcon;

public class Client extends Account {
  private Boolean isOfAge;

  public Client(String firstname, String lastname, String phoneNumber, String password, LocalDate dateOfBirth) {
    super(firstname, lastname, phoneNumber, password, dateOfBirth);
    //System.out.println("dateofbirth in client class constructor: " + dateOfBirth);
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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public Boolean getIsOfAge() {
    return this.isOfAge;
  }

  // public ArrayList<Booking> viewBookings() {
  //   return this.bookings;
  // }

  // public void makeBooking(Offering offering) throws UnderageException {
  //   if (!this.isOfAge) {
  //     throw new UnderageException("Underage user needs their guardian to handle their booking");
  //   }
  //   Booking newBooking = new Booking(offering);
  //   this.bookings.add(newBooking);
  // }

  // public void makeBookingForChild(Offering offering, Client minorClient)
  //     throws UnderageException, CantHandleBookingForAdultException {
  //   if (!this.isOfAge) {
  //     throw new UnderageException("Underage user cannot handle a booking for someone else");
  //   } else if (minorClient.getIsOfAge()) {
  //     throw new CantHandleBookingForAdultException("Adult users must handle their own bookings");
  //   }
  //   Booking newBooking = new Booking(offering);
  //   minorClient.bookings.add(newBooking);
  // }

  // public void cancelBooking(Booking booking) {
  //   this.bookings.remove(booking);
  // }

  public String toString() {
    return null;
  }
}