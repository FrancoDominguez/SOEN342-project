package lessonbooking.CLI;

import lessonbooking.services.ClientService;
import lessonbooking.services.InstructorService;
import lessonbooking.services.AdministratorService;
import java.util.Scanner;

public class LoginMenu {
  public void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.println("\n\nWelcome to Lesson Booking System");
      System.out.println("1. Client Login");
      System.out.println("2. Instructor Login");
      System.out.println("3. Administrator Login");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          try {
            ClientService clientService = new ClientService();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            clientService.login(username, password);

            ClientMenu clientMenu = new ClientMenu(clientService);
            clientMenu.startMenu();
          } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
          }
          break;

        case 2:
          try {
            InstructorService instructorService = new InstructorService();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            instructorService.login(username, password);

            InstructorMenu instructorMenu = new InstructorMenu(instructorService);
            instructorMenu.startMenu();
          } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
          }
          break;

        case 3:
          try {
            AdministratorService adminService = new AdministratorService();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            adminService.login(username, password);

            AdministratorMenu adminMenu = new AdministratorMenu(adminService);
            adminMenu.startMenu();
          } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
          }
          break;

        case 4:
          running = false;
          System.out.println("Exiting the system. Goodbye!");
          break;

        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }

    scanner.close();
  }
}
