package main.java;

import java.util.Scanner;
import main.java.models.Client;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      // Display menu
      System.out.println("Welcome to the booking app");
      System.out.println("1. Login");
      System.out.println("2. Register");
      System.out.println("3. Make a Booking");
      System.out.println("4. Exit");
      System.out.print("Please select an option: ");

      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          login();
          break;
        case 2:
          createAccount(scanner);
          break;
        case 3:
          makeBooking();
          break;
        case 4:
          System.out.println("Thank you for using the App. Goodbye!");
          exit = true;
          break;
        default:
          System.out.println("Invalid option. Please try again.");
      }

      System.out.println();
    }

    scanner.close();
  }

  public static void login() {
    // Implement login functionality here
    System.out.println("Login functionality goes here.");
  }

  public static void createAccount(Scanner scanner) {
    scanner.nextLine();
    System.out.print("Enter first name: ");
    String firstname = scanner.nextLine();
    System.out.print("Enter last name: ");
    String lastname = scanner.nextLine();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    System.out.print("Enter date of birth (yyyy-MM-dd): ");
    String dateOfBirth = scanner.nextLine();
    Client newClientAccount = new Client(firstname, lastname, phoneNumber, password, dateOfBirth);
    newClientAccount.register();
    System.out.println("Your account has been created");
  }

  public static void makeBooking() {

    // Implement booking functionality here
    System.out.println("Booking functionality goes here.");
  }
}
