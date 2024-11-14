package lessonbooking.services;

import java.util.ArrayList;

import lessonbooking.DAO.BookingsDAO;
import lessonbooking.DAO.ClientsDAO;
import lessonbooking.models.Booking;
import lessonbooking.models.Client;
import lessonbooking.models.Offering;

public class ClientService extends GuestService {
  private Client client;
  private BookingsDAO bookingsCatalog;
  private ClientsDAO clientsCatalog;

  public ClientService() {
    super();
    this.clientsCatalog = new ClientsDAO();
    this.bookingsCatalog = new BookingsDAO();
  }

  public void login(String username, String password) throws Exception {
    Client client = clientsCatalog.fetchByUsername(username);
    if (client == null) {
      throw new Exception(String.format("Client account with username '%s' was not found", username));
    } else if (!client.validatePassword(password)) {
      throw new Exception("Incorrect password");
    } else {
      this.client = client;
    }
  }

  public ArrayList<Booking> viewBookings() {
    return this.bookingsCatalog.fetchByClientId(this.client.getId());
  }

  private void performBooking(Client clientGettingBooked, Offering offering) throws Exception {
    if (!offering.isAvailable()) {
      throw new Exception("Offering is unavailable");
    } else {
      offering.addParticipant();
      this.offeringsCatalog.save(offering);
      this.bookingsCatalog.insert(clientGettingBooked.getId(), offering.getId());
    }
  }

  public void makeBooking(Offering offering) throws Exception {
    if (!client.isAdult()) {
      throw new Exception("Minors need an endorser to handle their bookings");
    } else {
      performBooking(this.client, offering);
    }
  }

  public void makeBookingForMinor(String minorUsername, Offering offering) throws Exception {
    Client minor = this.clientsCatalog.fetchByUsername(minorUsername);
    if (!this.client.isAdult()) {
      throw new Exception("Endorser must be an adult");
    } else if (minor.isAdult()) {
      throw new Exception("Adults must handle their own bookings");
    } else {
      performBooking(minor, offering);
    }
  }

  public void cancelBooking(Booking booking) {
    try {
      Offering offering = this.offeringsCatalog.fetchById(booking.getOfferingId());
      offering.removeParticipant();
      offeringsCatalog.save(offering);
      this.bookingsCatalog.delete(booking);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
