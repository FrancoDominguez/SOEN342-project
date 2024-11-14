package lessonbooking.models;

import java.time.LocalDateTime;

public class Offering {
  private int id;
  private String lessonType;
  private String privatePublic;
  private boolean isAvailable;
  private int maxParticipants;
  private int participants;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private int locationId;
  private String locationName;
  private String locationAddress;
  private int instructorId;
  private String instructorFirstname;
  private String instructorLastname;

  public Offering(String lessonType, String privatePublic, int maxParticipants,
      LocalDateTime startTime, LocalDateTime endTime, int locationId, String locationName,
      String locationAddress) {
    this.id = -1;
    this.lessonType = lessonType;
    this.privatePublic = privatePublic;
    this.isAvailable = false;
    this.maxParticipants = maxParticipants;
    this.participants = 0;
    this.startTime = startTime;
    this.endTime = endTime;
    this.locationId = locationId;
    this.locationName = locationName;
    this.locationAddress = locationAddress;
    this.instructorId = -1;
    this.instructorFirstname = null;
    this.instructorLastname = null;
  }

  public Offering(int id, String lessonType, String privatePublic, boolean isAvailable, int maxParticipants,
      int participants, LocalDateTime startTime, LocalDateTime endTime, int locationId, String locationName,
      String locationAddress,
      int instructorId, String instructorFirstname, String instructorLastname) {
    this.id = id;
    this.lessonType = lessonType;
    this.privatePublic = privatePublic;
    this.isAvailable = isAvailable;
    this.maxParticipants = maxParticipants;
    this.participants = participants;
    this.startTime = startTime;
    this.endTime = endTime;
    this.locationId = locationId;
    this.locationName = locationName;
    this.locationAddress = locationAddress;
    this.instructorId = instructorId;
    this.instructorFirstname = instructorFirstname;
    this.instructorLastname = instructorLastname;
  }

  public int getId() {
    return id;
  }

  public String getLessonType() {
    return lessonType;
  }

  public String getPrivatePublic() {
    return privatePublic;
  }

  public int getMaxParticipants() {
    return maxParticipants;
  }

  public int getParticipants() {
    return participants;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public int getLocationId() {
    return this.locationId;
  }

  public String getLocationName() {
    return locationName;
  }

  public String getLocationAddress() {
    return locationAddress;
  }

  public int getInstructorId() {
    return this.instructorId;
  }

  public String getInstructorFirstname() {
    return instructorFirstname;
  }

  public String getInstructorLastname() {
    return instructorLastname;
  }

  public void addParticipants() throws Exception {

  }

  public Boolean isFull() {
    return (this.maxParticipants == this.participants);
  }

  public Boolean hasInstructor() {
    return (this.instructorId != -1);
  }

  public Boolean isAvailable() {
    return (!this.isFull() && this.hasInstructor());
  }

  @Override
  public String toString() {
    return "Offering{" +
        ", lessonType='" + lessonType + '\'' +
        ", privatePublic='" + privatePublic + '\'' +
        ", isAvailable=" + isAvailable +
        ", maxParticipants=" + maxParticipants +
        ", participants=" + participants +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", locationName='" + locationName + '\'' +
        ", locationAddress='" + locationAddress + '\'' +
        ", instructorFirstname='" + instructorFirstname + '\'' +
        ", instructorLastname='" + instructorLastname + '\'' +
        '}';
  }
}