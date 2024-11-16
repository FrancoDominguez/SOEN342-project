package lessonbooking.CLI;

import lessonbooking.services.GuestService;
import lessonbooking.models.Location;
import lessonbooking.models.Offering;
import lessonbooking.models.Organization;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private GuestService guestService;
    private RegistrationMenu registrationMenu;
    private LoginMenu loginMenu;

    public MainMenu() {
        this.guestService = new GuestService();
        this.registrationMenu = new RegistrationMenu();
        this.loginMenu = new LoginMenu();
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Main Menu:");
            System.out.println("1. View All Offerings");
            System.out.println("2. View Offerings By City");
            System.out.println("3. View Offerings By Lesson Type");
            System.out.println("4. View Empty Offerings");
            System.out.println("5. View Locations");
            System.out.println("6. View Organizations");
            System.out.println("7. Go to Registration Menu");
            System.out.println("8. Go to Login Menu");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ArrayList<Offering> allOfferings = guestService.viewAllOfferings();
                    printArray(allOfferings);
                    break;
                case 2:
                    System.out.print("Enter city name: ");
                    String city = scanner.nextLine();
                    ArrayList<Offering> offeringsByCity = guestService.viewOfferingsByCity(city);
                    printArray(offeringsByCity);
                    break;
                case 3:
                    System.out.print("Enter lesson type: ");
                    String lessonType = scanner.nextLine();
                    ArrayList<Offering> offeringsByType = guestService.viewOfferingsByLessonType(lessonType);
                    printArray(offeringsByType);
                    break;
                case 4:
                    ArrayList<Offering> emptyOfferings = guestService.viewEmptyOfferings();
                    printArray(emptyOfferings);
                    break;
                case 5:
                    ArrayList<Location> locations = guestService.viewLocations();
                    printArray(locations);
                    break;
                case 6:
                    ArrayList<Organization> organizations = guestService.viewOrganizations();
                    printArray(organizations);
                    break;
                case 7:
                    registrationMenu.startMenu();
                    break;
                case 8:
                    loginMenu.startMenu();
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private <T> void printArray(ArrayList<T> arraylist) {
        if (arraylist.isEmpty()) {
            System.out.println("No items to display.");
        } else {
            for (T item : arraylist) {
                System.out.println(item.toString());
            }
        }
    }
}
