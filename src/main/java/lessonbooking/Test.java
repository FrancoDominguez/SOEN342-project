package lessonbooking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static lessonbooking.utils.PrintArray.printArray;

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
    GuestService guestService = new GuestService();
    RegistrationService registrationService = new RegistrationService();
    ClientService clientService = new ClientService();
    AdministratorService adminService = new AdministratorService();

    // Resources
    ArrayList<Offering> offerings = guestService.viewAllOfferings();
    ArrayList<Organization> organizations = guestService.viewOrganizations();
    ArrayList<Location> locations = guestService.viewLocations();

    try {
      ArrayList<String> cities1 = new ArrayList<>(List.of("Montreal", "Toronto"));
      ArrayList<String> cities2 = new ArrayList<>(List.of("Montreal", "Pointe Claire"));
      ArrayList<String> cities3 = new ArrayList<>(List.of("Montreal", "Pierrefonds"));
      ArrayList<String> cities4 = new ArrayList<>(List.of("Montreal", "Beaconsfield", "Pierrefonds"));
      ArrayList<String> cities5 = new ArrayList<>(List.of("Toronto"));

      InstructorService instructorService = new InstructorService();
      System.out.println("creating instructors");
      registrationService.registerInstructor("john_doe", "John", "Doe", "1234567890", "supersafe",
          LocalDate.parse("1985-05-10"), "swimming", cities1);
      registrationService.registerInstructor("jane_smith", "Jane", "Smith", "0987654321", "supersafe",
          LocalDate.parse("1990-08-20"), "yoga", cities2);
      registrationService.registerInstructor("alex_jones", "Alex", "Jones", "5555555555", "supersafe",
          LocalDate.parse("1988-12-15"), "karate", cities3);
      registrationService.registerInstructor("emily_clark", "Emily", "Clark", "4444444444", "supersafe",
          LocalDate.parse("1995-07-05"), "swimming", cities4);
      registrationService.registerInstructor("mike_wilson", "Mike", "Wilson", "3333333333", "supersafe",
          LocalDate.parse("1982-03-30"), "yoga", cities5);
      AdministratorService admin = new AdministratorService();
      admin.login("FrancoDominguez", "supersafe");
      // delete offerings
      // delete offerings --force
      // view offering bookings
      // delete booking
      // delete account

      ClientService client = new ClientService();
      client.login("JohnDoe", "supersafe");
      // view bookings
      // make booking
      // make booking for minor
      // cancel booking

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
