package main.java.models;

import main.java.exceptions.InvalidInstructorException;
import java.util.ArrayList;
import java.util.UUID;

public class Offering {
  private String offeringId;
  private String mode;
  private String lessonType;
  private Boolean isAvailable;
  private TimeSlot classTime;
  private ArrayList<Instructor> instructors;
  private Location location;
  private int numOfParticipants;

  public Offering(String mode, String lessonType, Location location, TimeSlot classTime) {
    this.offeringId = UUID.randomUUID().toString();
    this.mode = mode;
    this.lessonType = lessonType;
    this.isAvailable = false;
    this.location = location;
    this.classTime = classTime;
    this.instructors = new ArrayList<Instructor>();
    this.numOfParticipants = 0;
  }

  public void assignInstructor(Instructor instructor) throws InvalidInstructorException {
    if (!instructor.getSpecialization().equals(lessonType)) {
      throw new InvalidInstructorException(
          String.format("Instructor with specialization '%s' cannot teach class of type '%s'",
              instructor.getSpecialization(), this.lessonType));
    }
    this.instructors.add(instructor);
    if (!this.isAvailable) {
      // no max space limitation constraint
      this.isAvailable = true;
    }
  }

  public TimeSlot getClassTime() {
    return this.classTime;
  }

  public String toString() {
    return String.format("offeringId: %s, mode: %s, lessonType: %s, isAvailable: %s",
        offeringId, mode, lessonType, isAvailable);
  }
}
