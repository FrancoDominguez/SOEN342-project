package lessonbooking.models;

import java.time.LocalDateTime;

public class TimeSlot {
  private int id;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Location location;

  public TimeSlot(LocalDateTime startTime, LocalDateTime endTime, Location location) {
    this.id = -1;
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
  }

  public TimeSlot(int id, LocalDateTime startTime, LocalDateTime endTime, Location location) {
    this.id = id;
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
  }

  public int getId() {
    return this.id;
  }

  public LocalDateTime getStartTime() {
    return this.startTime;
  }

  public LocalDateTime getEndTime() {
    return this.endTime;
  }

  public Location getLocation() {
    return this.location;
  }
}
