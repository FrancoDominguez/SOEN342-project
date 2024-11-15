package lessonbooking.CLI;

import lessonbooking.models.Location;
import lessonbooking.models.Offering;
import lessonbooking.models.Organization;
import lessonbooking.services.GuestService;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestMenu {
    public void startMenu() {
        GuestService guestService = new GuestService();
        RegistrationMenu registrationMenu = new RegistrationMenu();
        LoginMenu loginMenu = new LoginMenu();
        Scanner scanner = new Scanner(System.in);

        ArrayList<Location> locations = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. View Locations");
            System.out.println("2. View Offerings by Location");
            System.out.println("3. View Offerings by Lesson Type");
            System.out.println("4. View Offerings by City");
            System.out.println("5. View All Offerings");
            System.out.println("6. View Organizations");
            System.out.println("7. Registration Menu");
            System.out.println("8. Login Menu");
            System.out.println("9. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    locations = guestService.viewLocations();
                    if (locations != null && !locations.isEmpty()) {
                        for (int i = 0; i < locations.size(); i++) {
                            System.out.println((i + 1) + ". " + locations.get(i).toString());
                        }
                    } else {
                        System.out.println("No locations available.");
                    }
                    break;

                case 2:
                    if (locations == null || locations.isEmpty()) {
                        System.out.println("You must view locations first.");
                    } else {
                        System.out.print("Choose a location by number: ");
                        int locationChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (locationChoice > 0 && locationChoice <= locations.size()) {
                            Location selectedLocation = locations.get(locationChoice - 1);
                            ArrayList<Offering> offerings = guestService.viewOfferingsByLocation(selectedLocation);
                            offerings.forEach(o -> System.out.println(o.toString()));
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter lesson type: ");
                    String lessonType = scanner.nextLine();
                    ArrayList<Offering> offeringsByType = guestService.viewOfferingsByLessonType(lessonType);
                    offeringsByType.forEach(o -> System.out.println(o.toString()));
                    break;

                case 4:
                    System.out.print("Enter city name: ");
                    String cityName = scanner.nextLine();
                    ArrayList<Offering> offeringsByCity = guestService.viewOfferingsByCity(cityName);
                    offeringsByCity.forEach(o -> System.out.println(o.toString()));
                    break;

                case 5:
                    ArrayList<Offering> allOfferings = guestService.viewAllOfferings();
                    allOfferings.forEach(o -> System.out.println(o.toString()));
                    break;

                case 6:
                    ArrayList<Organization> organizations = guestService.viewOrganizations();
                    organizations.forEach(o -> System.out.println(o.toString()));
                    break;

                case 7:
                    registrationMenu.startMenu();
                    break;

                case 8:
                    loginMenu.startMenu();
                    break;

                case 9:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
