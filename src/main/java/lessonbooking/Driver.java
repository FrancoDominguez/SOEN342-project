package lessonbooking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import lessonbooking.models.Client;
import lessonbooking.models.Instructor;
import lessonbooking.services.Mysqlcon;

public class Driver {
  public static void main(String[] args) throws Exception {
    Mysqlcon mysql = new Mysqlcon();
    mysql.connect();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      Mysqlcon connection = new Mysqlcon();

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
    //System.out.println("Login functionality goes here.");
    
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.nextLine();  // Assuming phone number is the unique identifier
    
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    // Create a MySQL connection to check if the user exists and if the password matches
    Mysqlcon mysql = new Mysqlcon();
    try {
        mysql.connect();
        
        // Query to check if the user with the provided phone number exists
        String query = "SELECT * FROM clients WHERE phone_number = ? AND password = ?";
        
        // Use a prepared statement to prevent SQL injection
        PreparedStatement pstmt = mysql.getConnection().prepareStatement(query);
        pstmt.setString(1, phoneNumber);
        pstmt.setString(2, password);
        
        // Execute the query
        ResultSet rs = pstmt.executeQuery();

        // Check if the result set contains any rows (user exists and password matches)
        if (rs.next()) {
            System.out.println("Login successful!");
            // You can add further logic here like creating a session, etc.
        } else {
            System.out.println("Invalid phone number or password. Please try again.");
        }

    } catch (Exception e) {
        System.out.println("Error during login: " + e.getMessage());
    } finally {
        try {
            mysql.close();
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
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
    String dateOfBirthInput = scanner.nextLine(); 

    LocalDate dateOfBirth = null;
        try {
            // Parse the string to LocalDate using a specific date format
            dateOfBirth = LocalDate.parse(dateOfBirthInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        } 

    System.out.print("Press 1 if you a client or Press 2 if you are an Instructor 2: ");
    int acc_type = scanner.nextInt();

   
    if (acc_type == 1){
      Client newClientAccount = new Client(firstname, lastname, phoneNumber, password, dateOfBirth);
      newClientAccount.register();
    }

    if (acc_type == 2) {
      System.out.print("Please enter your specialization: ");
      String specialization = scanner.nextLine();

      //System.out.print("Please enter your city(): ");
      //String cities = scanner.nextLine();
      

      //REMEMBER TO ADD CITIES AFTER
      Instructor newInstructorAccount = new Instructor(firstname, lastname, phoneNumber, password, dateOfBirth, specialization);
      newInstructorAccount.register();
    }

    
    
    
  }

  public static void makeBooking() {
    // Implement booking functionality here
    System.out.println("Booking functionality goes here.");
  }
}
