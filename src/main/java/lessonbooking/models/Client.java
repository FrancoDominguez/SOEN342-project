package lessonbooking.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client extends Account {
  private ArrayList<Booking> bookings;

  public Client(String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth) {
    super(username, firstname, lastname, phoneNumber, password, dateOfBirth);
    bookings = new ArrayList<Booking>();
  }

  public Client(int id, String username, String firstname, String lastname, String phoneNumber, String password,
      LocalDate dateOfBirth) {
    super(id, username, firstname, lastname, phoneNumber, password, dateOfBirth);
    bookings = new ArrayList<Booking>();
  }

  public ArrayList<Booking> viewBookings() {
    return this.bookings;
  }

  public void addBooking(Booking booking) throws Exception {
    if (!this.isOfAge()) {
      throw new Exception("If the client is under 18 they need an adult endorser to handle their bookings");
    } else {
      this.bookings.add(booking);
    }
  }

  public void addBooking(Client endorser, Booking booking) throws Exception {
    if (!endorser.isOfAge()) {
      throw new Exception("The endorser must be over 18");
    } else {
      this.bookings.add(booking);
    }
  }

  public void removeBooking(Booking booking) {
    this.bookings.remove(booking);
  }
}