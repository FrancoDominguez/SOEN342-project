package lessonbooking.CLI;

import lessonbooking.services.RegistrationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationMenu {
  public RegistrationMenu() {
  }

  public void startMenu() {
    Scanner scanner = new Scanner(System.in);
    RegistrationService service = new RegistrationService();
    boolean exit = false;

    while (!exit) {
      System.out.println("\nLesson Booking System Menu:");
      System.out.println("1. Register Client");
      System.out.println("2. Register Instructor");
      System.out.println("3. Register Administrator");
      System.out.println("4. Exit");
      System.out.print("Choose an option: ");
      int choice = Integer.parseInt(scanner.nextLine());

      try {
        switch (choice) {
          case 1 -> {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter firstname: ");
            String firstname = scanner.nextLine();
            System.out.print("Enter lastname: ");
            String lastname = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter date of birth (yyyy-mm-dd): ");
            LocalDate dob = LocalDate.parse(scanner.nextLine());

            service.registerClient(username, firstname, lastname, phoneNumber, password, dob);
            System.out.println("Client registered successfully.");
          }
          case 2 -> {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter firstname: ");
            String firstname = scanner.nextLine();
            System.out.print("Enter lastname: ");
            String lastname = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter date of birth (yyyy-mm-dd): ");
            LocalDate dob = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter specialization: ");
            String specialization = scanner.nextLine();
            System.out.print("Enter cities (comma-separated): ");
            ArrayList<String> cities = new ArrayList<>();
            for (String city : scanner.nextLine().split(",")) {
              cities.add(city.trim());
            }

            service.registerInstructor(username, firstname, lastname, phoneNumber, password, dob, specialization,
                cities);
            System.out.println("Instructor registered successfully.");
          }
          case 3 -> {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter firstname: ");
            String firstname = scanner.nextLine();
            System.out.print("Enter lastname: ");
            String lastname = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter date of birth (yyyy-mm-dd): ");
            LocalDate dob = LocalDate.parse(scanner.nextLine());

            service.registerAdministrator(username, firstname, lastname, phoneNumber, password, dob);
            System.out.println("Administrator registered successfully.");
          }
          case 4 -> exit = true;
          default -> System.out.println("Invalid option. Please try again.");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }

    scanner.close();
    System.out.println("Goodbye!");
  }
}
