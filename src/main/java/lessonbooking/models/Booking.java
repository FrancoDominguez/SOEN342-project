package lessonbooking.models;

import java.time.LocalDateTime;

public class Booking {
  private int clientId;
  private int offeringId;
  private String clientUsername;
  private String clientFirstname;
  private String clientLastname;
  private String lessonType;
  private String privatePublic;
  private boolean isAvailable;
  private int maxParticipants;
  private int participants;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String locationName;
  private String locationAddress;
  private String instructorFirstname;
  private String instructorLastname;

  public Booking(int clientId, int offeringId, String clientUsername, String clientFirstname, String clientLastname,
      String lessonType, String privatePublic, boolean isAvailable, int maxParticipants, int participants,
      LocalDateTime startTime, LocalDateTime endTime, String locationName, String locationAddress,
      String instructorFirstname, String instructorLastname) {
    this.clientId = clientId;
    this.offeringId = offeringId;
    this.clientUsername = clientUsername;
    this.clientFirstname = clientFirstname;
    this.clientLastname = clientLastname;
    this.lessonType = lessonType;
    this.privatePublic = privatePublic;
    this.isAvailable = isAvailable;
    this.maxParticipants = maxParticipants;
    this.participants = participants;
    this.startTime = startTime;
    this.endTime = endTime;
    this.locationName = locationName;
    this.locationAddress = locationAddress;
    this.instructorFirstname = instructorFirstname;
    this.instructorLastname = instructorLastname;
  }

  public int getClientId() {
    return clientId;
  }

  public int getOfferingId() {
    return offeringId;
  }

  public String getClientUsername() {
    return clientUsername;
  }

  public String getClientFirstname() {
    return clientFirstname;
  }

  public String getClientLastname() {
    return clientLastname;
  }

  public String getLessonType() {
    return lessonType;
  }

  public String getPrivatePublic() {
    return privatePublic;
  }

  public boolean isAvailable() {
    return isAvailable;
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

  public String getLocationName() {
    return locationName;
  }

  public String getLocationAddress() {
    return locationAddress;
  }

  public String getInstructorFirstname() {
    return instructorFirstname;
  }

  public String getInstructorLastname() {
    return instructorLastname;
  }

  @Override
  public String toString() {
    return "Client (ID: " + this.clientId + "): " + this.clientFirstname + " " + this.clientLastname +
        ", Offering ID: " + this.offeringId +
        ", Type: " + this.lessonType +
        ", Privacy: " + this.privatePublic +
        ", Participants: " + this.participants + "/" + this.maxParticipants +
        ", Time: " + this.startTime + " to " + this.endTime +
        ", Location: " + this.locationName + " (" + this.locationAddress + ")" +
        ", Instructor: " + this.instructorFirstname + " " + this.instructorLastname;
  }
}