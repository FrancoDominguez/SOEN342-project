package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lessonbooking.models.Offering;
import lessonbooking.services.Mysqlcon;

public class OfferingsDAO {
  public OfferingsDAO() {
  }

  public ArrayList<Offering> fetchByLocationId(int locationId) throws Exception {
    ArrayList<Offering> offerings = new ArrayList<Offering>();
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "SELECT offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, " +
            "offerings.is_available, offerings.max_participants, offerings.participants, " +
            "offerings.start_time, offerings.end_time, " +
            "locations.id AS location_id, locations.name AS location_name, locations.address, " +
            "instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, " +
            "instructors.lastname AS instructor_lastname " +
            "FROM offerings " +
            "JOIN locations ON offerings.location_id = locations.id " +
            "JOIN instructor_offering ON offerings.id = instructor_offering.offering_id " +
            "JOIN instructors ON instructor_offering.instructor_id = instructors.id " +
            "WHERE offerings.location_id = %d;",
        locationId);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    while (rs.next()) {
      int offeringId = rs.getInt("offering_id");
      String lessonType = rs.getString("lesson_type");
      String privatePublic = rs.getString("private_public");
      boolean isAvailable = rs.getBoolean("is_available");
      int maxParticipants = rs.getInt("max_participants");
      int participants = rs.getInt("participants");
      LocalDateTime startTime = rs.getTimestamp("start_time") != null
          ? rs.getTimestamp("start_time").toLocalDateTime()
          : null;
      LocalDateTime endTime = rs.getTimestamp("end_time") != null ? rs.getTimestamp("end_time").toLocalDateTime()
          : null;
      String locationName = rs.getString("location_name");
      String locationAddress = rs.getString("address");
      int instructorId = rs.getInt("instructor_id");
      String instructorFirstname = rs.getString("instructor_firstname");
      String instructorLastname = rs.getString("instructor_lastname");

      Offering newOffering = new Offering(offeringId, lessonType, privatePublic, isAvailable, maxParticipants,
          participants, startTime, endTime, locationId, locationName, locationAddress, instructorId,
          instructorFirstname, instructorLastname);
      offerings.add(newOffering);
    }
    con.close();
    return offerings;
  }

  public void insert(Offering offering) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "INSERT INTO offerings (lesson_type, private_public, is_available, location_id, max_participants, participants, start_time, end_time) "
            +
            "VALUES ('%s', '%s', %b, %d, %d, %d, '%s', '%s')",
        offering.getLessonType(),
        offering.getPrivatePublic(),
        offering.isAvailable(),
        offering.getLocationId(),
        offering.getMaxParticipants(),
        offering.getParticipants(),
        offering.getStartTime(),
        offering.getEndTime());
    con.executeUpdate(queryString);
    con.close();
  }

  public void save(Offering offering) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "UPDATE offerings SET lesson_type = '%s', private_public = '%s', is_available = %b, location_id = %d, " +
            "max_participants = %d, participants = %d, start_time = '%s', end_time = '%s' WHERE id = %d",
        offering.getLessonType(),
        offering.getPrivatePublic(),
        offering.isAvailable(),
        offering.getLocationId(),
        offering.getMaxParticipants(),
        offering.getParticipants(),
        offering.getStartTime(),
        offering.getEndTime(),
        offering.getId());
    con.executeUpdate(queryString);
    con.close();
  }

  public void delete(int offeringId) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("DELETE FROM offerings WHERE offering_id = %d", offeringId);
    con.executeUpdate(queryString);
    con.close();
  }
}