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
  private String locationCity;
  private int instructorId;
  private String instructorFirstname;
  private String instructorLastname;
  private Boolean availableForClient;

  public Offering(String lessonType, String privatePublic, int maxParticipants,
      LocalDateTime startTime, LocalDateTime endTime, Location location) {
    this.id = -1;
    this.lessonType = lessonType;
    this.privatePublic = privatePublic;
    this.isAvailable = false;
    this.maxParticipants = maxParticipants;
    this.participants = 0;
    this.startTime = startTime;
    this.endTime = endTime;
    this.locationId = location.getId();
    this.locationName = location.getName();
    this.locationAddress = location.getAddress();
    this.locationCity = location.getCity();
    this.instructorId = -1;
    this.instructorFirstname = null;
    this.instructorLastname = null;
  }

  public Offering(int id, String lessonType, String privatePublic, boolean isAvailable, int maxParticipants,
      int participants, LocalDateTime startTime, LocalDateTime endTime, int locationId, String locationName,
      String locationAddress, String locationCity,
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
    this.locationCity = locationCity;
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

  public String getLocationCity() {
    return this.locationCity;
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

  public void addParticipant() {
    this.participants++;
  }

  public void removeParticipant() {
    this.participants--;
  }

  public void assignInstructor(Instructor instructor) {
    this.instructorId = instructor.getId();
    this.instructorFirstname = instructor.getFirstname();
    this.instructorLastname = instructor.getLastname();
  }

  public void dropInstructor() {
    this.instructorId = -1;
    this.instructorFirstname = null;
    this.instructorLastname = null;
  }

  public Boolean isFull() {
    return (this.maxParticipants == this.participants);
  }

  public Boolean isEmpty() {
    return (this.participants == 0);
  }

  public Boolean hasInstructor() {
    return (this.instructorId > 0);
  }

  public Boolean isAvailable() {
    return (!this.isFull() && this.hasInstructor());
  }

  public void setAvailableForClient(Boolean bool) {
    this.availableForClient = bool;
  }

  @Override
  public String toString() {
    Boolean availability;
    if (this.availableForClient == null) {
      availability = isAvailable() ? true : false;
    } else {
      availability = this.availableForClient ? (isAvailable() ? true : false) : false;
    }

    return "ID: " + id + ", " +
        (availability ? "Available" : "Unavailable") +
        ", Type: " + lessonType +
        ", Privacy: " + privatePublic +
        ", Max Participants: " + maxParticipants +
        ", Current Participants: " + participants +
        ", Time: " + startTime + " to " + endTime +
        ", Location: " + locationName + " (" + locationAddress + ", " + locationCity + ")" +
        ", Instructor: " + instructorFirstname + " " + instructorLastname + " (ID: " + instructorId + ")";
  }
}
