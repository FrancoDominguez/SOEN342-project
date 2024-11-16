package lessonbooking.CLI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import lessonbooking.models.*;
import lessonbooking.services.AdministratorService;
import static lessonbooking.utils.Utils.printArray;

public class AdminMenu {
  private AdministratorService adminService;

  public AdminMenu(AdministratorService adminService) {
    this.adminService = adminService;
  }

  private void viewAdminInfo() {
    try {
      Administrator admin = adminService.accessInfo();
      if (admin == null) {
        System.out.println("No administrator data available.");
      } else {
        System.out.println("Administrator Info:");
        System.out.println(admin.toString());
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.println("\n\nAdministrator Menu:");
      System.out.println("1. View Admin Info");
      System.out.println("2. Create Offering");
      System.out.println("3. Delete Offering");
      System.out.println("4. View All Offerings");
      System.out.println("5. View Empty Offerings");
      System.out.println("6. View Offerings by Location");
      System.out.println("7. View Offerings by Lesson Type");
      System.out.println("8. View Offerings by City");
      System.out.println("9. Create Organization");
      System.out.println("10. Create Location");
      System.out.println("11. View Locations");
      System.out.println("12. View Organizations");
      System.out.println("13. Delete Account");
      System.out.println("14. Exit");
      System.out.print("Choose an option: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      try {
        switch (choice) {
          case 1:
            viewAdminInfo();
            break;
          case 2:
            System.out.print("Enter lesson type: ");
            String lessonType = scanner.nextLine();
            System.out.print("Enter private/public: ");
            String privatePublic = scanner.nextLine();
            System.out.print("Enter max participants: ");
            int maxParticipants = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter start time (YYYY-MM-DDTHH:MM): ");
            LocalDateTime startTime = LocalDateTime.parse(scanner.nextLine());
            System.out.print("Enter end time (YYYY-MM-DDTHH:MM): ");
            LocalDateTime endTime = LocalDateTime.parse(scanner.nextLine());
            System.out.println("Choose a location:");
            ArrayList<Location> locations = adminService.viewLocations();
            printArray("Locations", locations);
            System.out.print("Enter location index: ");
            Location location = locations.get(scanner.nextInt());
            scanner.nextLine();
            adminService.createOffering(lessonType, privatePublic, maxParticipants, startTime, endTime, location);
            System.out.println("Offering created successfully.");
            break;
          case 3:
            System.out.println("Choose an offering:");
            ArrayList<Offering> offerings = adminService.viewAllOfferings();
            printArray("Offerings", offerings);
            System.out.print("Enter offering index: ");
            Offering offeringToDelete = offerings.get(scanner.nextInt());
            scanner.nextLine();
            adminService.deleteOffering(offeringToDelete);
            System.out.println("Offering deleted successfully.");
            break;
          case 4:
            ArrayList<Offering> allOfferings = adminService.viewAllOfferings();
            printArray("All Offerings", allOfferings);
            System.out.println("Offerings displayed successfully.");
            break;
          case 5:
            ArrayList<Offering> emptyOfferings = adminService.viewEmptyOfferings();
            printArray("Empty Offerings", emptyOfferings);
            System.out.println("Empty offerings displayed successfully.");
            break;
          case 6:
            System.out.println("Choose a location:");
            ArrayList<Location> locs = adminService.viewLocations();
            printArray("Locations", locs);
            System.out.print("Enter location index: ");
            Location loc = locs.get(scanner.nextInt());
            scanner.nextLine();
            ArrayList<Offering> locOfferings = adminService.viewOfferingsByLocation(loc);
            printArray("Offerings by Location", locOfferings);
            System.out.println("Offerings by location displayed successfully.");
            break;
          case 7:
            System.out.print("Enter lesson type: ");
            String type = scanner.nextLine();
            ArrayList<Offering> typeOfferings = adminService.viewOfferingsByLessonType(type);
            printArray("Offerings by Lesson Type", typeOfferings);
            System.out.println("Offerings by lesson type displayed successfully.");
            break;
          case 8:
            System.out.print("Enter city name: ");
            String city = scanner.nextLine();
            ArrayList<Offering> cityOfferings = adminService.viewOfferingsByCity(city);
            printArray("Offerings by City", cityOfferings);
            System.out.println("Offerings by city displayed successfully.");
            break;
          case 9:
            System.out.print("Enter organization name: ");
            String orgName = scanner.nextLine();
            adminService.createOrganization(orgName);
            System.out.println("Organization created successfully.");
            break;
          case 10:
            System.out.print("Enter location name: ");
            String locName = scanner.nextLine();
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            System.out.print("Enter city: ");
            String cityName = scanner.nextLine();
            System.out.println("Choose an organization:");
            ArrayList<Organization> orgs = adminService.viewOrganizations();
            printArray("Organizations", orgs);
            System.out.print("Enter organization index: ");
            int orgId = orgs.get(scanner.nextInt()).getId();
            scanner.nextLine();
            adminService.createLocation(locName, address, cityName, orgId);
            System.out.println("Location created successfully.");
            break;
          case 11:
            ArrayList<Location> allLocations = adminService.viewLocations();
            printArray("Locations", allLocations);
            System.out.println("Locations displayed successfully.");
            break;
          case 12:
            ArrayList<Organization> allOrganizations = adminService.viewOrganizations();
            printArray("Organizations", allOrganizations);
            System.out.println("Organizations displayed successfully.");
            break;
          case 13:
            System.out.print("Enter account username: ");
            String username = scanner.nextLine();
            System.out.print("Enter account type (client/administrator/instructor): ");
            String accountType = scanner.nextLine();
            adminService.deleteAccount(username, accountType);
            System.out.println("Account deleted successfully.");
            break;
          case 14:
            running = false;
            System.out.println("Exiting menu.");
            break;
          default:
            System.out.println("Invalid choice. Please try again.");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}
