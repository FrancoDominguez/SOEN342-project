package lessonbooking.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lessonbooking.DAO.AdministratorsDAO;
import lessonbooking.DAO.BookingsDAO;
import lessonbooking.DAO.ClientsDAO;
import lessonbooking.DAO.InstructorOfferingsDAO;
import lessonbooking.DAO.InstructorsDAO;
import lessonbooking.DAO.OrganizationsDAO;
import lessonbooking.models.Administrator;
import lessonbooking.models.Booking;
import lessonbooking.models.Client;
import lessonbooking.models.Location;
import lessonbooking.models.Offering;
import lessonbooking.models.Organization;

public class AdministratorService extends GuestService {
  private Administrator admin;
  private BookingsDAO bookingsCatalog;
  private AdministratorsDAO administratorsCatalog;
  private ClientsDAO clientsCatalog;
  private InstructorsDAO instructorsCatalog;
  private InstructorOfferingsDAO instructorOfferingsCatalog;

  public AdministratorService() {
    this.bookingsCatalog = new BookingsDAO();
    this.administratorsCatalog = new AdministratorsDAO();
    this.clientsCatalog = new ClientsDAO();
    this.instructorsCatalog = new InstructorsDAO();
    this.instructorOfferingsCatalog = new InstructorOfferingsDAO();
  }

  // tested
  public void login(String username, String password) throws Exception {
    Administrator admin = administratorsCatalog.fetchByUsername(username);
    if (admin == null) {
      throw new Exception(String.format("Admin account with username '%s' was not found", username));
    } else if (!admin.validatePassword(password)) {
      throw new Exception("Incorrect password");
    } else {
      this.admin = admin;
    }
  }

  // tested
  public void createOffering(String lessonType, String privatePublic, int maxParticipants,
      LocalDateTime startTime, LocalDateTime endTime, Location location) {

    Offering newOffering = new Offering(
        lessonType, privatePublic, maxParticipants, startTime, endTime,
        location);
    try {
      this.offeringsCatalog.insert(newOffering);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteOffering(Offering offering) throws Exception {
    if (!offering.isEmpty()) {
      throw new Exception("Cannot delete offering with active bookings");
    } else {
      if (offering.hasInstructor()) {
        this.instructorOfferingsCatalog.delete(offering.getId());
      }
      this.offeringsCatalog.delete(offering.getId());
    }
  }

  public void deleteOfferingForce(Offering offering, Boolean force) {
    try {
      if (offering.hasInstructor()) {
        this.instructorOfferingsCatalog.delete(offering.getId());
      }
      this.bookingsCatalog.deleteFromOffering(offering.getId());
      this.offeringsCatalog.delete(offering.getId());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // tested
  public void createOrganization(String name) {
    Organization org = new Organization(name);
    try {
      organizationsCatalog.insert(org);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // tested
  public void createLocation(String name, String address, String city, int organizationId) {
    Location newLocation = new Location(name, address, city, organizationId);
    try {
      this.locationsCatalog.insert(newLocation);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteAccount(String username, String accountType) {
    // interfacing needed to implement this
  }

  public ArrayList<Booking> viewOfferingBookings(Offering offering) {
    return this.bookingsCatalog.fetchByOfferingId(offering.getId());
  }

  public void deleteBooking(Booking booking) throws Exception {
    this.bookingsCatalog.delete(booking);
  }
}