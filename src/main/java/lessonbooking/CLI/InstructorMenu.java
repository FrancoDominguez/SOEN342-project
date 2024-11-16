package lessonbooking.CLI;

import lessonbooking.services.InstructorService;
import lessonbooking.models.Instructor;
import lessonbooking.models.Offering;
import static lessonbooking.utils.Utils.printArray;

import java.util.ArrayList;
import java.util.Scanner;

public class InstructorMenu {
  private InstructorService instructorService;

  public InstructorMenu(InstructorService instructorService) {
    this.instructorService = instructorService;
  }

  public void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      System.out.println("\n\nInstructor Menu:");
      System.out.println("1. Take Offering");
      System.out.println("2. Drop Offering");
      System.out.println("3. View Available Offerings");
      System.out.println("4. View Assigned Offerings"); // New option
      System.out.println("5. View Instructor Info");
      System.out.println("6. Back to Previous Menu");
      System.out.print("Enter your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          takeOffering(scanner);
          break;
        case 2:
          dropOffering(scanner);
          break;
        case 3:
          viewAvailableOfferings();
          break;
        case 4:
          viewAssignedOfferings(); // New case
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

  private void takeOffering(Scanner scanner) {
    try {
      ArrayList<Offering> availableOfferings = instructorService.viewAvailableOfferings();
      if (availableOfferings.isEmpty()) {
        System.out.println("No available offerings to take.");
        return;
      }
      printArray("Available Offerings", availableOfferings);
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
      ArrayList<Offering> assignedOfferings = instructorService.viewAssignedOfferings(); // Use new function
      if (assignedOfferings.isEmpty()) {
        System.out.println("No assigned offerings to drop.");
        return;
      }
      printArray("Assigned Offerings", assignedOfferings);
      System.out.print("Enter the ID of the offering to drop: ");
      int offeringId = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      Offering selectedOffering = findOfferingById(assignedOfferings, offeringId);
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
      printArray("Available Offerings", availableOfferings);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void viewAssignedOfferings() {
    try {
      ArrayList<Offering> assignedOfferings = instructorService.viewAssignedOfferings(); // Call new function
      if (assignedOfferings.isEmpty()) {
        System.out.println("No assigned offerings found.");
      } else {
        printArray("Assigned Offerings", assignedOfferings);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void viewInstructorInfo() {
    try {
      Instructor instructor = instructorService.accessInfo();
      if (instructor == null) {
        System.out.println("No instructor data available.");
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
}
