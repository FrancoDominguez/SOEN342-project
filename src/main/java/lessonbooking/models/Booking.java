package lessonbooking.models;

import java.util.ArrayList;
import java.util.UUID;

public class Booking {
  
  private String id;
  private Offering offering;
  private String privatePublic;
  private String lessonType;
  private TimeSlot classTime;
  private ArrayList<Instructor> instructors;

  // constructor for new object
  public Booking(Offering offering) {
    this.id = UUID.randomUUID().toString();
    this.offering = offering;
    this.privatePublic = offering.getprivatePublic();
    this.lessonType = offering.getLessonType();
    this.classTime = offering.getClassTime();
    this.instructors = offering.getInstructors();
  }

  // constructor when pulling from the db
  public Booking(String id, Offering offering) {
    this.id = id;
    this.privatePublic = offering.getprivatePublic();
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
        ", privatePublic='" + privatePublic + '\'' +
        ", lessonType='" + lessonType + '\'' +
        ", classTime=" + classTime +
        ", instructors=" + instructors +
        '}';
  }
}