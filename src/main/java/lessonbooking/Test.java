package lessonbooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lessonbooking.utils.Utils.printArray;
import static lessonbooking.utils.Utils.random;

import lessonbooking.models.Booking;
import lessonbooking.models.Location;
import lessonbooking.models.Offering;
import lessonbooking.models.Organization;
import lessonbooking.services.AdministratorService;
import lessonbooking.services.ClientService;
import lessonbooking.services.GuestService;
import lessonbooking.services.InstructorService;
import lessonbooking.services.Mysqlcon;
import lessonbooking.services.RegistrationService;

public class Test {
  public Test() {
  }

  public void runTest() {

    try {
      ClientService client = new ClientService();
      AdministratorService admin = new AdministratorService();
      InstructorService instructor = new InstructorService();
      RegistrationService registrator = new RegistrationService();

      // misc methods
      // registrator.registerClient("ClientToBeDeleted", "Firstname", "Lastname",
      // "1231231234", "supersafe",
      // LocalDate.parse("1990-01-01"));
      // registrator.registerClient("MinorClient", "Firstname", "Lastname",
      // "1231231234", "supersafe",
      // LocalDate.parse("2010-01-01"));

      GuestService guest = new GuestService();
      ArrayList<Location> allLocations = guest.viewLocations();
      ArrayList<Organization> allOrganizations = guest.viewOrganizations();

      // Administrator methods
      admin.login("FrancoDominguez", "supersafe");
      admin.createOffering("karate", "public", 20, LocalDateTime.of(2024, 11, 20, 10, 0),
          LocalDateTime.of(2024, 11, 20, 12, 0), new GuestService().viewLocations().get(random(allLocations.size())));
      admin.createOffering("karate", "public", 20, LocalDateTime.of(2024, 11, 20, 10, 0),
          LocalDateTime.of(2024, 11, 20, 12, 0), new GuestService().viewLocations().get(random(allLocations.size())));

      printArray("list of empty offerings", admin.viewEmptyOfferings());
      admin.deleteOffering(admin.viewEmptyOfferings().get(0));
      printArray("list of empty offerings after deletion", admin.viewEmptyOfferings());

      admin.deleteAccount("ClientToBeDeleted", "client");

      // delete offerings
      // delete offerings --force
      // view offering bookings
      // delete booking
      // delete account

      instructor.login("JohnathanDoe", "password");
      ArrayList<Offering> availableOfferings = instructor.viewAvailableOfferings();
      instructor.takeOffering(availableOfferings.get(random(availableOfferings.size())));

      // Client methods
      client.login("JohnDoe", "supersafe");
      ArrayList<Offering> clientOfferings = client.viewAllOfferings();
      printArray("client's available offerings", clientOfferings);
      client.makeBooking(clientOfferings.get(0));
      ArrayList<Booking> bookings = client.viewBookings();
      printArray("client's bookings after making a booking", bookings);
      client.cancelBooking(bookings.get(0));
      bookings = client.viewBookings();
      printArray("client's bookings after canceling that booking", bookings);

      // make booking for minor

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void clearTable(String tableName) {
    Mysqlcon con = new Mysqlcon();
    try {
      con.connect();
      String queryString = String.format("DELETE FROM %s;", tableName);
      con.executeUpdate(queryString);
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
