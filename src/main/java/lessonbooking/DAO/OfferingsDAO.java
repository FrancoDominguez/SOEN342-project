package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lessonbooking.models.Offering;
import lessonbooking.services.Mysqlcon;

public class OfferingsDAO {
  public OfferingsDAO() {
  }

  private ArrayList<Offering> fetchOfferings(String queryString) {
    ArrayList<Offering> offerings = new ArrayList<>();
    Mysqlcon con = new Mysqlcon();
    try {
      con.connect();
      con.executeQuery(queryString);
      ResultSet rs = con.getResultSet();
      while (rs.next()) {
        offerings.add(parseResultSet(rs));
      }
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return offerings;
  }

  private Offering parseResultSet(ResultSet rs) throws Exception {
    int offeringId = rs.getInt("offering_id");
    String lessonType = rs.getString("lesson_type");
    String privatePublic = rs.getString("private_public");
    boolean isAvailable = rs.getBoolean("is_available");
    int maxParticipants = rs.getInt("max_participants");
    int participants = rs.getInt("participants");
    LocalDateTime startTime = rs.getTimestamp("start_time") != null
        ? rs.getTimestamp("start_time").toLocalDateTime()
        : null;
    LocalDateTime endTime = rs.getTimestamp("end_time") != null
        ? rs.getTimestamp("end_time").toLocalDateTime()
        : null;
    int locationId = rs.getInt("location_id");
    String locationName = rs.getString("location_name");
    String locationAddress = rs.getString("address");
    String locationCity = rs.getString("city");
    int instructorId = rs.getInt("instructor_id");
    String instructorFirstname = rs.getString("instructor_firstname");
    String instructorLastname = rs.getString("instructor_lastname");

    return new Offering(offeringId, lessonType, privatePublic, isAvailable, maxParticipants, participants,
        startTime, endTime, locationId, locationName, locationAddress, locationCity,
        instructorId, instructorFirstname, instructorLastname);
  }

  public ArrayList<Offering> fetchByLocationId(int locationId) {
    String queryString = String.format(
        "SELECT offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, " +
            "offerings.is_available, offerings.max_participants, offerings.participants, " +
            "offerings.start_time, offerings.end_time, " +
            "locations.id AS location_id, locations.name AS location_name, locations.address, locations.city, " +
            "instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, " +
            "instructors.lastname AS instructor_lastname " +
            "FROM offerings " +
            "JOIN locations ON offerings.location_id = locations.id " +
            "JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
            "JOIN instructors ON instructor_offerings.instructor_id = instructors.id " +
            "WHERE offerings.location_id = %d;",
        locationId);
    return fetchOfferings(queryString);
  }

  public ArrayList<Offering> fetchByLessonType(String lessonType) {
    String queryString = String.format(
        "SELECT offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, " +
            "offerings.is_available, offerings.max_participants, offerings.participants, " +
            "offerings.start_time, offerings.end_time, " +
            "locations.id AS location_id, locations.name AS location_name, locations.address, locations.city, " +
            "instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, " +
            "instructors.lastname AS instructor_lastname " +
            "FROM offerings " +
            "LEFT JOIN locations ON offerings.location_id = locations.id " +
            "LEFT JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
            "LEFT JOIN instructors ON instructor_offerings.instructor_id = instructors.id " +
            "WHERE offerings.lesson_type = '%s';",
        lessonType);
    return fetchOfferings(queryString);
  }

  public ArrayList<Offering> fetchByCity(String city) {
    String queryString = String.format(
        "SELECT offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, " +
            "offerings.is_available, offerings.max_participants, offerings.participants, " +
            "offerings.start_time, offerings.end_time, " +
            "locations.id AS location_id, locations.name AS location_name, locations.address, locations.city, " +
            "instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, " +
            "instructors.lastname AS instructor_lastname " +
            "FROM offerings " +
            "LEFT JOIN locations ON offerings.location_id = locations.id " +
            "LEFT JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
            "LEFT JOIN instructors ON instructor_offerings.instructor_id = instructors.id " +
            "WHERE locations.city LIKE '%%%s%%';",
        city);
    return fetchOfferings(queryString);
  }

  public ArrayList<Offering> fetchAll() {
    String queryString = "SELECT offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, " +
        "offerings.is_available, offerings.max_participants, offerings.participants, " +
        "offerings.start_time, offerings.end_time, " +
        "locations.id AS location_id, locations.name AS location_name, locations.address, locations.city, " +
        "instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, " +
        "instructors.lastname AS instructor_lastname " +
        "FROM offerings " +
        "LEFT JOIN locations ON offerings.location_id = locations.id " +
        "LEFT JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
        "LEFT JOIN instructors ON instructor_offerings.instructor_id = instructors.id;";
    return fetchOfferings(queryString);
  }

  public Offering fetchById(int offeringId) {
    String queryString = String.format(
        "SELECT offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, " +
            "offerings.is_available, offerings.max_participants, offerings.participants, " +
            "offerings.start_time, offerings.end_time, " +
            "locations.id AS location_id, locations.name AS location_name, locations.address, locations.city, " +
            "instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, " +
            "instructors.lastname AS instructor_lastname " +
            "FROM offerings " +
            "LEFT JOIN locations ON offerings.location_id = locations.id " +
            "LEFT JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
            "LEFT JOIN instructors ON instructor_offerings.instructor_id = instructors.id " +
            "WHERE offerings.id = %d;",
        offeringId);
    ArrayList<Offering> offerings = fetchOfferings(queryString);
    return offerings.isEmpty() ? null : offerings.get(0);
  }

  public ArrayList<Offering> fetchByInstructorId(int instructorId) {
    String queryString = String.format(
        "SELECT offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, " +
            "offerings.is_available, offerings.max_participants, offerings.participants, " +
            "offerings.start_time, offerings.end_time, " +
            "locations.id AS location_id, locations.name AS location_name, locations.address, locations.city, " +
            "instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, " +
            "instructors.lastname AS instructor_lastname " +
            "FROM offerings " +
            "LEFT JOIN locations ON offerings.location_id = locations.id " +
            "LEFT JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
            "LEFT JOIN instructors ON instructor_offerings.instructor_id = instructors.id " +
            "WHERE instructors.id = %d;",
        instructorId);
    return fetchOfferings(queryString);
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
    String queryString = String.format("DELETE FROM offerings WHERE id = %d", offeringId);
    con.executeUpdate(queryString);
    con.close();
  }
}