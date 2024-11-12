package lessonbooking.models;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lessonbooking.exceptions.CantHandleBookingForAdultException;
import lessonbooking.exceptions.UnderageException;
import lessonbooking.services.Mysqlcon;

public class Client extends Account {
  private LocalDate dateOfBirth;
  private Boolean isOfAge;

  public Client(String firstname, String lastname, String phoneNumber, String password,
      String dateOfBirth) {
    super(firstname, lastname, phoneNumber, password);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);

    LocalDate now = LocalDate.now();
    Period age = Period.between(this.dateOfBirth, now);
    this.isOfAge = (age.getYears() > 18);
  }

  public LocalDate getDateOfBirth() {
    return this.dateOfBirth;
  }

  public Boolean getIsOfAge() {
    return this.isOfAge;
  }

  public ArrayList<Booking> viewBookings() {
    return this.bookings;
  }

  public void makeBooking(Offering offering) throws UnderageException {
    if (!this.isOfAge) {
      throw new UnderageException("Underage user needs their guardian to handle their booking");
    }
    Booking newBooking = new Booking(offering);
    this.bookings.add(newBooking);
  }

  public void makeBookingForChild(Offering offering, Client minorClient)
      throws UnderageException, CantHandleBookingForAdultException {
    if (!this.isOfAge) {
      throw new UnderageException("Underage user cannot handle a booking for someone else");
    } else if (minorClient.getIsOfAge()) {
      throw new CantHandleBookingForAdultException("Adult users must handle their own bookings");
    }
    Booking newBooking = new Booking(offering);
    minorClient.bookings.add(newBooking);
  }

  public void cancelBooking(Booking booking) {
    this.bookings.remove(booking);
  }

  public String toString() {
    return null;
  }
}