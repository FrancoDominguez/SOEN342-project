package lessonbooking.CLI;

import lessonbooking.services.RegistrationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationMenu {
  private RegistrationService registrationService;

  public RegistrationMenu() {
    this.registrationService = new RegistrationService();
  }

  public void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      System.out.println("Registration Menu:");
      System.out.println("1. Register Client");
      System.out.println("2. Register Instructor");
      System.out.println("3. Register Administrator");
      System.out.println("4. Back to Previous Menu");
      System.out.print("Enter your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          registerClient(scanner);
          break;
        case 2:
          registerInstructor(scanner);
          break;
        case 3:
          registerAdministrator(scanner);
          break;
        case 4:
          exit = true;
          System.out.println("Returning to previous menu...");
          break;
        default:
          System.out.println("Invalid choice. Try again.");
      }
    }
  }

  private void registerClient(Scanner scanner) {
    try {
      System.out.println("Register Client:");
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter first name: ");
      String firstname = scanner.nextLine();
      System.out.print("Enter last name: ");
      String lastname = scanner.nextLine();
      System.out.print("Enter phone number: ");
      String phoneNumber = scanner.nextLine();
      System.out.print("Enter password: ");
      String password = scanner.nextLine();
      System.out.print("Enter date of birth (YYYY-MM-DD): ");
      LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

      registrationService.registerClient(username, firstname, lastname, phoneNumber, password, dateOfBirth);
      System.out.println("Client registered successfully.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void registerInstructor(Scanner scanner) {
    try {
      System.out.println("Register Instructor:");
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter first name: ");
      String firstname = scanner.nextLine();
      System.out.print("Enter last name: ");
      String lastname = scanner.nextLine();
      System.out.print("Enter phone number: ");
      String phoneNumber = scanner.nextLine();
      System.out.print("Enter password: ");
      String password = scanner.nextLine();
      System.out.print("Enter date of birth (YYYY-MM-DD): ");
      LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
      System.out.print("Enter specialization: ");
      String specialization = scanner.nextLine();
      System.out.print("Enter cities (comma-separated): ");
      String[] citiesInput = scanner.nextLine().split(",");
      ArrayList<String> cities = new ArrayList<>();
      for (String city : citiesInput) {
        cities.add(city.trim());
      }

      registrationService.registerInstructor(username, firstname, lastname, phoneNumber, password, dateOfBirth,
          specialization, cities);
      System.out.println("Instructor registered successfully.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void registerAdministrator(Scanner scanner) {
    try {
      System.out.println("Register Administrator:");
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter first name: ");
      String firstname = scanner.nextLine();
      System.out.print("Enter last name: ");
      String lastname = scanner.nextLine();
      System.out.print("Enter phone number: ");
      String phoneNumber = scanner.nextLine();
      System.out.print("Enter password: ");
      String password = scanner.nextLine();
      System.out.print("Enter date of birth (YYYY-MM-DD): ");
      LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

      registrationService.registerAdministrator(username, firstname, lastname, phoneNumber, password, dateOfBirth);
      System.out.println("Administrator registered successfully.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
