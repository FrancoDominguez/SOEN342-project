package lessonbooking.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lessonbooking.services.Mysqlcon;

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

  public void save() {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      Mysqlcon con = new Mysqlcon();
      con.connect();
      String queryString;
      if (this.id == -1) {
        queryString = String.format("INSERT INTO timeslots (location_id, start_time, end_time) VALUES (%d, %d, %d)",
            this.location.getId(), this.startTime.format(formatter), this.endTime.format(formatter));
      } else {
        queryString = String.format("UPDATE timeslots SET location_id = %d, start_time = %d, end_time %d WHERE id = %d",
            this.location.getId(), this.startTime.format(formatter), this.endTime.format(formatter), this.id);
      }
      con.executeUpdate(queryString);
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
