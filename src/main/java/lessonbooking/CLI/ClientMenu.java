package lessonbooking.CLI;

import lessonbooking.models.Booking;
import lessonbooking.models.Location;
import lessonbooking.models.Offering;
import lessonbooking.models.Client;
import lessonbooking.services.ClientService;
import static lessonbooking.utils.Utils.printArray;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientMenu {
  private ClientService clientService;

  public ClientMenu(ClientService clientService) {
    this.clientService = clientService;
  }

  public void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    int choice;

    while (running) {
      System.out.println("\n\nClient Menu:");
      System.out.println("1. View Bookings");
      System.out.println("2. Make Booking");
      System.out.println("3. Make Booking for Minor");
      System.out.println("4. Cancel Booking");
      System.out.println("5. View Offerings by Location");
      System.out.println("6. View Offerings by Lesson Type");
      System.out.println("7. View Offerings by City");
      System.out.println("8. View all Available Offerings");
      System.out.println("9. View Client Info");
      System.out.println("10. Go Back");

      System.out.print("Enter your choice: ");
      choice = Integer.parseInt(scanner.nextLine());

      try {
        switch (choice) {
          case 1:
            ArrayList<Booking> bookings = clientService.viewBookings();
            printArray("Bookings", bookings);
            break;
          case 2:
            System.out.println("\nChoose Offering View Method:");
            System.out.println("1. View all Available Offerings");
            System.out.println("2. View Offerings by Location");
            System.out.println("3. View Offerings by Lesson Type");
            System.out.println("4. View Offerings by City");
            System.out.print("Enter your choice: ");
            int viewChoice = Integer.parseInt(scanner.nextLine());
            ArrayList<Offering> offerings = new ArrayList<>();
            switch (viewChoice) {
              case 1:
                offerings = clientService.viewAllOfferings();
                break;
              case 2:
                ArrayList<Location> locations = clientService.viewLocations();
                printArray("available locations", locations);
                System.out.print("Enter Location index: ");
                int locationIndex = Integer.parseInt(scanner.nextLine());
                offerings = clientService.viewOfferingsByLocation(locations.get(locationIndex));
                break;
              case 3:
                System.out.print("Enter Lesson Type: ");
                String lessonType = scanner.nextLine();
                offerings = clientService.viewOfferingsByLessonType(lessonType);
                break;
              case 4:
                System.out.print("Enter City Name: ");
                String cityName = scanner.nextLine();
                offerings = clientService.viewOfferingsByCity(cityName);
                break;
              default:
                System.out.println("Invalid choice. Returning to main menu.");
                break;
            }
            if (!offerings.isEmpty()) {
              printArray("Offerings", offerings);
              System.out.print("Enter Offering Index: ");
              int offeringIndex = Integer.parseInt(scanner.nextLine());
              clientService.makeBooking(offerings.get(offeringIndex));
              System.out.println("Booking successful!");
            }
            break;

          case 3:
            System.out.println("\nChoose Offering View Method:");
            System.out.println("1. View all Available Offerings");
            System.out.println("2. View Offerings by Location");
            System.out.println("3. View Offerings by Lesson Type");
            System.out.println("4. View Offerings by City");
            System.out.print("Enter your choice: ");
            int minorViewChoice = Integer.parseInt(scanner.nextLine());
            ArrayList<Offering> minorOfferings = new ArrayList<>();
            switch (minorViewChoice) {
              case 1:
                minorOfferings = clientService.viewAllOfferings();
                break;
              case 2:
                ArrayList<Location> locations = clientService.viewLocations();
                printArray("available locations", locations);
                System.out.print("Enter Location index: ");
                int locationIndex = Integer.parseInt(scanner.nextLine());
                minorOfferings = clientService.viewOfferingsByLocation(locations.get(locationIndex));
                break;
              case 3:
                System.out.print("Enter Lesson Type: ");
                String lessonType = scanner.nextLine();
                minorOfferings = clientService.viewOfferingsByLessonType(lessonType);
                break;
              case 4:
                System.out.print("Enter City Name: ");
                String cityName = scanner.nextLine();
                minorOfferings = clientService.viewOfferingsByCity(cityName);
                break;
              default:
                System.out.println("Invalid choice. Returning to main menu.");
                break;
            }
            if (!minorOfferings.isEmpty()) {
              printArray("Offerings", minorOfferings);
              System.out.print("Enter Minor's Username: ");
              String minorUsername = scanner.nextLine();
              System.out.print("Enter Offering Index: ");
              int minorOfferingIndex = Integer.parseInt(scanner.nextLine());
              clientService.makeBookingForMinor(minorUsername, minorOfferings.get(minorOfferingIndex));
              System.out.println("Booking for minor successful!");
            }
            break;
          case 4:
            ArrayList<Booking> allBookings = clientService.viewBookings();
            printArray("your bookings", allBookings);
            System.out.print("Enter Booking Index: ");
            int bookingIndex = Integer.parseInt(scanner.nextLine());
            clientService.cancelBooking(allBookings.get(bookingIndex));
            System.out.println("Booking canceled!");
            break;
          case 5:
            ArrayList<Location> locations = this.clientService.viewLocations();
            printArray("available locations", locations);
            System.out.print("Enter Location index: ");
            int locationIndex = Integer.parseInt(scanner.nextLine());
            ArrayList<Offering> locationOfferings = clientService.viewOfferingsByLocation(locations.get(locationIndex));
            printArray("Offerings by Location", locationOfferings);
            break;
          case 6:
            System.out.print("Enter Lesson Type: ");
            String lessonType = scanner.nextLine();
            ArrayList<Offering> lessonOfferings = clientService.viewOfferingsByLessonType(lessonType);
            printArray("Offerings by Lesson Type", lessonOfferings);
            break;
          case 7:
            System.out.print("Enter City Name: ");
            String cityName = scanner.nextLine();
            ArrayList<Offering> cityOfferings = clientService.viewOfferingsByCity(cityName);
            printArray("Offerings by City", cityOfferings);
            break;
          case 8:
            ArrayList<Offering> allOfferingsList = clientService.viewAllOfferings();
            printArray("All Offerings", allOfferingsList);
            break;
          case 9:
            viewClientInfo();
            break;
          case 10:
            System.out.println("Returning to previous menu.");
            running = false;
            break;
          default:
            System.out.println("Invalid choice. Please try again.");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  private void viewClientInfo() {
    try {
      Client client = clientService.accessInfo();
      if (client == null) {
        System.out.println("No client data available.");
      } else {
        System.out.println("Client Info:");
        System.out.println(client.toString());
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
