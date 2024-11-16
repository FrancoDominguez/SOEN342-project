package lessonbooking.CLI;

import lessonbooking.services.InstructorService;
import lessonbooking.models.Instructor;
import lessonbooking.models.Offering;

import java.util.ArrayList;
import java.util.Scanner;

public class InstructorMenu {
  private InstructorService instructorService;

  public InstructorMenu() {
    this.instructorService = new InstructorService();
  }

  public void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      System.out.println("Instructor Menu:");
      System.out.println("1. Login");
      System.out.println("2. Take Offering");
      System.out.println("3. Drop Offering");
      System.out.println("4. View Available Offerings");
      System.out.println("5. View Instructor Info");
      System.out.println("6. Back to Previous Menu");
      System.out.print("Enter your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          login(scanner);
          break;
        case 2:
          takeOffering(scanner);
          break;
        case 3:
          dropOffering(scanner);
          break;
        case 4:
          viewAvailableOfferings();
          break;
        case 5:
          viewInstructorInfo();
          break;
        case 6:
          exit = true;
          System.out.println("Returning to previous menu...");
          break;
        default:
          System.out.println("Invalid choice. Try again.");
      }
    }
  }

  private void login(Scanner scanner) {
    try {
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter password: ");
      String password = scanner.nextLine();
      instructorService.login(username, password);
      System.out.println("Login successful.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void takeOffering(Scanner scanner) {
    try {
      System.out.println("Take Offering:");
      ArrayList<Offering> availableOfferings = instructorService.viewAvailableOfferings();
      if (availableOfferings.isEmpty()) {
        System.out.println("No available offerings to take.");
        return;
      }
      printArray(availableOfferings);
      System.out.print("Enter the ID of the offering to take: ");
      int offeringId = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      Offering selectedOffering = findOfferingById(availableOfferings, offeringId);
      if (selectedOffering == null) {
        System.out.println("Offering not found.");
        return;
      }

      instructorService.takeOffering(selectedOffering);
      System.out.println("Offering successfully taken.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void dropOffering(Scanner scanner) {
    try {
      System.out.println("Drop Offering:");
      ArrayList<Offering> currentOfferings = instructorService.viewOfferingsByLessonType("All");
      if (currentOfferings.isEmpty()) {
        System.out.println("No offerings to drop.");
        return;
      }
      printArray(currentOfferings);
      System.out.print("Enter the ID of the offering to drop: ");
      int offeringId = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      Offering selectedOffering = findOfferingById(currentOfferings, offeringId);
      if (selectedOffering == null) {
        System.out.println("Offering not found.");
        return;
      }

      instructorService.dropOffering(selectedOffering);
      System.out.println("Offering successfully dropped.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void viewAvailableOfferings() {
    try {
      System.out.println("Available Offerings:");
      ArrayList<Offering> availableOfferings = instructorService.viewAvailableOfferings();
      printArray(availableOfferings);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void viewInstructorInfo() {
    try {
      Instructor instructor = instructorService.accessInfo();
      if (instructor == null) {
        System.out.println("No instructor logged in.");
      } else {
        System.out.println("Instructor Info:");
        System.out.println(instructor.toString());
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private Offering findOfferingById(ArrayList<Offering> offerings, int id) {
    for (Offering offering : offerings) {
      if (offering.getId() == id) {
        return offering;
      }
    }
    return null;
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
