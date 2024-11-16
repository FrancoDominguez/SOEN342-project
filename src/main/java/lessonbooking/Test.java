package lessonbooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
      AdministratorService admin = new AdministratorService();

      GuestService guest = new GuestService();
      ArrayList<Location> allLocations = guest.viewLocations();

      // Administrator methods
      admin.login("FrancoDominguez", "supersafe");
      LocalDateTime now = LocalDateTime.now();

      int randomMinutes = ThreadLocalRandom.current().nextInt(1, 121);
      LocalDateTime startTime = now.plusMinutes(randomMinutes);

      LocalDateTime endTime = startTime.plusMinutes(ThreadLocalRandom.current().nextInt(1, 121));

      admin.createOffering("yoga", "public", 20, startTime, endTime,
          new GuestService().viewLocations().get(random(allLocations.size())));
      admin.createOffering("yoga", "public", 20, startTime, endTime,
          new GuestService().viewLocations().get(random(allLocations.size())));
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
