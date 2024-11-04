package models;

import java.util.ArrayList;
import java.util.UUID;

public class Booking {
  private String id;
  private Offering offering;
  private String mode;
  private String lessonType;
  private TimeSlot classTime;
  private ArrayList<Instructor> instructors;

  // constructor for new object
  public Booking(Offering offering) {
    this.id = UUID.randomUUID().toString();
    this.offering = offering;
    this.mode = offering.getMode();
    this.lessonType = offering.getLessonType();
    this.classTime = offering.getClassTime();
    this.instructors = offering.getInstructors();
  }

  // constructor when pulling from the db
  public Booking(String id, Offering offering) {
    this.id = id;
    this.mode = offering.getMode();
    this.lessonType = offering.getLessonType();
    this.classTime = offering.getClassTime();
    this.instructors = offering.getInstructors();
    this.offering = offering;
  }

  public void cancel() {
    // delete from db and remove reference from client
  }

  @Override
  public String toString() {
    return "Booking{" +
        "id='" + id + '\'' +
        ", offering=" + offering +
        ", mode='" + mode + '\'' +
        ", lessonType='" + lessonType + '\'' +
        ", classTime=" + classTime +
        ", instructors=" + instructors +
        '}';
  }
}