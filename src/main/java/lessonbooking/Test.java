package lessonbooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lessonbooking.models.Location;
import lessonbooking.models.Organization;
import lessonbooking.services.AdministratorService;
import lessonbooking.services.Mysqlcon;
import lessonbooking.services.RegistrationService;

public class Test {
  public Test() {
  }

  public void runTest() {
    try {
      System.out.println("Clearing all tables: ");
      clearTable("bookings");
      System.out.println("Cleared table: bookings");
      clearTable("instructor_offerings");
      System.out.println("Cleared table: instructor_offerings");
      clearTable("offerings");
      System.out.println("Cleared table: offerings");
      clearTable("clients");
      System.out.println("Cleared table: clients");
      clearTable("instructor_cities");
      System.out.println("Cleared table: instructor_cities");
      clearTable("administrators");
      System.out.println("Cleared table: administrators");
      clearTable("instructors");
      System.out.println("Cleared table: instructors");
      clearTable("locations");
      System.out.println("Cleared table: locations");
      clearTable("organizations");
      System.out.println("Cleared table: organizations");
      System.out.println("\nAll tables have been cleared");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    RegistrationService registrator = new RegistrationService();

    try {
      System.out.println("Creating users...");

      // instructors
      ArrayList<String> cities1 = new ArrayList<>();
      cities1.add("Montreal");
      cities1.add("Griffintown");
      registrator.registerInstructor("PickleballInstructor", "Alex", "Jones", "5141231234", "supersafe",
          LocalDate.of(2000, 1, 1), "pickleball", cities1);

      ArrayList<String> cities2 = new ArrayList<>();
      cities2.add("Griffintown");
      registrator.registerInstructor("BasketballInstructor", "Katie", "Perry", "1234567894", "supersafe",
          LocalDate.of(2000, 5, 5), "basketball", cities2);

      ArrayList<String> cities3 = new ArrayList<>();
      cities3.add("Griffintown");
      registrator.registerInstructor("BasketballInstructor1", "John", "Doe", "1234567894", "supersafe",
          LocalDate.of(2000, 5, 5), "basketball", cities3);

      ArrayList<String> cities4 = new ArrayList<>();
      cities3.add("Montreal");
      cities3.add("Griffintown");
      registrator.registerInstructor("YogaInstructor", "Travis", "Scott", "4569871235", "supersafe",
          LocalDate.of(2000, 4, 4), "yoga", cities4);

      ArrayList<String> cities5 = new ArrayList<>();
      cities4.add("Montreal");
      registrator.registerInstructor("BoxingInstructor", "Alex", "Jones", "7896541235", "supersafe",
          LocalDate.of(2000, 3, 3), "boxing", cities5);

      ArrayList<String> cities6 = new ArrayList<>();
      cities5.add("Montreal");
      registrator.registerInstructor("SwimmingInstructor", "Michael", "Phelps", "8888444666", "supersafe",
          LocalDate.of(2000, 2, 2), "swimming", cities6);

      // clients
      registrator.registerClient("Client1", "Abby", "Faulkner", "5141231234", "supersafe",
          LocalDate.of(1990, 1, 1));
      registrator.registerClient("Client2", "Ashkan", "Forghani", "5141231234", "supersafe",
          LocalDate.of(1990, 1, 1));
      registrator.registerClient("Client3", "Borat", "Chimaev", "5141231234", "supersafe",
          LocalDate.of(1990, 1, 1));
      registrator.registerClient("Client4", "Bruno", "Mars", "5141231234", "supersafe",
          LocalDate.of(1990, 1, 1));
      registrator.registerClient("Minor", "Stuart", "Little", "5141231234", "supersafe",
          LocalDate.of(2010, 1, 1));

      // admin
      registrator.registerAdministrator("Admin", "John", "Doe", "5141231234", "supersafe",
          LocalDate.of(1990, 1, 1));

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }

    try {

      System.out.println("Creating organizations...");

      AdministratorService admin = new AdministratorService();
      admin.login("Admin", "supersafe");

      // organizations
      admin.createOrganization("The Booking Org");
      admin.createOrganization("The Lesson Org");

      System.out.println("Creating locations...");

      // locations
      List<Organization> organizations = admin.viewOrganizations();
      int orgId1 = organizations.get(0).getId();
      int orgId2 = organizations.get(1).getId();

      admin.createLocation("McGill University", "123 Maple Lane, Quebec, h1h1h1", "Montreal", orgId1);
      admin.createLocation("Concordia University", "514 Maple Street, Quebec, h9h9h9", "Montreal", orgId1);
      admin.createLocation("ETS", "514 Maple blvd, Quebec, h4h4h4", "Griffintown", orgId2);
      admin.createLocation("Bell Center", "438 Maple Ave, Quebec, h8h8h8", "Griffintown", orgId2);
      ArrayList<Location> locations = admin.viewLocations();

      System.out.println("Creating offerings...");

      // Concordia
      Location concordia = getLocationByName("Concordia University", locations);
      admin.createOffering("yoga", "public", 10,
          LocalDateTime.of(2024, 11, 30, 10, 20),
          LocalDateTime.of(2024, 11, 30, 11, 0),
          concordia);

      admin.createOffering("yoga", "public", 10,
          LocalDateTime.of(2024, 12, 7, 10, 20),
          LocalDateTime.of(2024, 12, 7, 11, 0),
          concordia);

      admin.createOffering("yoga", "public", 10,
          LocalDateTime.of(2024, 12, 14, 10, 20),
          LocalDateTime.of(2024, 12, 14, 11, 0),
          concordia);

      admin.createOffering("yoga", "public", 10,
          LocalDateTime.of(2024, 12, 21, 10, 20),
          LocalDateTime.of(2024, 12, 21, 11, 0),
          concordia);

      admin.createOffering("basketball", "public", 20,
          LocalDateTime.of(2024, 12, 2, 15, 0),
          LocalDateTime.of(2024, 12, 2, 16, 30),
          concordia);

      // Bell Center
      Location bellCenter = getLocationByName("Bell Center", locations);
      admin.createOffering("boxing", "private", 15,
          LocalDateTime.of(2024, 12, 5, 18, 0),
          LocalDateTime.of(2024, 12, 5, 19, 30),
          bellCenter);

      admin.createOffering("pickleball", "public", 12,
          LocalDateTime.of(2024, 12, 10, 20, 0),
          LocalDateTime.of(2024, 12, 10, 21, 30),
          bellCenter);

      admin.createOffering("swimming", "public", 10,
          LocalDateTime.of(2024, 12, 15, 16, 0),
          LocalDateTime.of(2024, 12, 15, 17, 30),
          bellCenter);

      // ETS
      Location ets = getLocationByName("ETS", locations);
      admin.createOffering("basketball", "public", 20,
          LocalDateTime.of(2024, 12, 8, 14, 0),
          LocalDateTime.of(2024, 12, 8, 15, 30),
          ets);

      admin.createOffering("yoga", "public", 10,
          LocalDateTime.of(2024, 12, 12, 17, 0),
          LocalDateTime.of(2024, 12, 12, 18, 30),
          ets);

      // McGill University
      Location mcGill = getLocationByName("McGill University", locations);
      admin.createOffering("pickleball", "public", 12,
          LocalDateTime.of(2024, 12, 6, 16, 0),
          LocalDateTime.of(2024, 12, 6, 17, 30),
          mcGill);

      admin.createOffering("boxing", "private", 15,
          LocalDateTime.of(2024, 12, 9, 18, 30),
          LocalDateTime.of(2024, 12, 9, 20, 0),
          mcGill);

      admin.createOffering("basketball", "public", 20,
          LocalDateTime.of(2024, 12, 13, 15, 0),
          LocalDateTime.of(2024, 12, 13, 16, 30),
          mcGill);

      admin.createOffering("swimming", "public", 10,
          LocalDateTime.of(2024, 12, 18, 19, 0),
          LocalDateTime.of(2024, 12, 18, 20, 30),
          mcGill);

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
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

  private Location getLocationByName(String name, ArrayList<Location> locations) {
    for (Location location : locations) {
      if (location.getName().equalsIgnoreCase(name)) {
        return location;
      }
    }
    return null; // Or throw an exception if not found
  }

}
