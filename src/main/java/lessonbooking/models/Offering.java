package lessonbooking.models;

import java.util.ArrayList;
import java.util.UUID;

import lessonbooking.exceptions.InvalidInstructorException;

public class Offering {
  private int id;
  private String privatePublic;
  private String lessonType;
  private Boolean isAvailable;
  private TimeSlot classTime;
  private ArrayList<Instructor> instructors;
  private Location location;
  private int numOfParticipants;

  public TimeSlot getClassTime() {
    return this.classTime;
  }

  public String getLessonType() {
    return this.lessonType;
  }

  public String getprivatePublic() {
    return this.privatePublic;
  }

  public ArrayList<Instructor> getInstructors() {
    return this.instructors;
  }

  // constructor for new object
  public Offering(String privatePublic, String lessonType, Location location, TimeSlot classTime) {
    this.id = -1;
    this.privatePublic = privatePublic;
    this.lessonType = lessonType;
    this.isAvailable = false;
    this.location = location;
    this.classTime = classTime;
    this.instructors = new ArrayList<Instructor>();
    this.numOfParticipants = 0;
  }

  // constructor when pulling from the db
  public Offering(int id, String privatePublic, String lessonType, Location location, TimeSlot classTime) {
    this.id = id;
    this.privatePublic = privatePublic;
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

  public String toString() {
    return String.format(
        "id: %s, privatePublic: %s, lessonType: %s, isAvailable: %s, classTime: %s, location: %s, numOfParticipants: %d, instructors: %s",
        id, privatePublic, lessonType, isAvailable, classTime, location, numOfParticipants, instructors);
  }
}
