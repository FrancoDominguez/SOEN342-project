package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lessonbooking.models.Booking;
import lessonbooking.services.Mysqlcon;

public class BookingsDAO {
  public BookingsDAO() {
  }

  private ArrayList<Booking> fetchBookings(String queryString) {
    ArrayList<Booking> bookings = new ArrayList<>();
    Mysqlcon con = new Mysqlcon();
    try {
      con.connect();
      con.executeQuery(queryString);
      ResultSet rs = con.getResultSet();
      while (rs.next()) {
        bookings.add(parseBookingResultSet(rs));
      }
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return bookings;
  }

  private Booking parseBookingResultSet(ResultSet rs) throws Exception {
    int clientId = rs.getInt("client_id");
    int offeringId = rs.getInt("offering_id");
    String clientUsername = rs.getString("username");
    String clientFirstname = rs.getString("firstname");
    String clientLastname = rs.getString("lastname");
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
    String locationName = rs.getString("location_name");
    String locationAddress = rs.getString("address");
    String instructorFirstname = rs.getString("instructor_firstname");
    String instructorLastname = rs.getString("instructor_lastname");

    return new Booking(clientId, offeringId, clientUsername, clientFirstname, clientLastname, lessonType,
        privatePublic, isAvailable, maxParticipants, participants, startTime, endTime, locationName, locationAddress,
        instructorFirstname, instructorLastname);
  }

  public ArrayList<Booking> fetchByClientId(int clientId) {
    String queryString = String.format(
        "SELECT bookings.client_id, clients.username, clients.firstname, clients.lastname, " +
            "offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, offerings.is_available, " +
            "offerings.max_participants, offerings.participants, offerings.start_time, offerings.end_time, " +
            "locations.name AS location_name, locations.address, " +
            "instructors.firstname AS instructor_firstname, instructors.lastname AS instructor_lastname " +
            "FROM bookings " +
            "JOIN clients ON bookings.client_id = clients.id " +
            "JOIN offerings ON bookings.offering_id = offerings.id " +
            "JOIN locations ON offerings.location_id = locations.id " +
            "JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
            "JOIN instructors ON instructor_offerings.instructor_id = instructors.id " +
            "WHERE bookings.client_id = %d;",
        clientId);
    return fetchBookings(queryString);
  }

  public ArrayList<Booking> fetchByOfferingId(int offeringId) {
    String queryString = String.format(
        "SELECT bookings.client_id, clients.username, clients.firstname, clients.lastname, " +
            "offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, offerings.is_available, " +
            "offerings.max_participants, offerings.participants, offerings.start_time, offerings.end_time, " +
            "locations.name AS location_name, locations.address, " +
            "instructors.firstname AS instructor_firstname, instructors.lastname AS instructor_lastname " +
            "FROM bookings " +
            "JOIN clients ON bookings.client_id = clients.id " +
            "JOIN offerings ON bookings.offering_id = offerings.id " +
            "JOIN locations ON offerings.location_id = locations.id " +
            "JOIN instructor_offerings ON offerings.id = instructor_offerings.offering_id " +
            "JOIN instructors ON instructor_offerings.instructor_id = instructors.id " +
            "WHERE bookings.offering_id = %d;",
        offeringId);
    return fetchBookings(queryString);
  }

  public void insert(int clientId, int offeringId) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("INSERT INTO bookings (client_id, offering_id) VALUES (%d, %d)",
        clientId, offeringId);
    con.executeUpdate(queryString);
    con.close();
  }

  public void delete(Booking booking) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("DELETE FROM bookings WHERE client_id = %d AND offering_id = %d",
        booking.getClientId(), booking.getOfferingId());
    con.executeUpdate(queryString);
    con.close();
  }

  public void deleteFromOffering(int offeringId) {
    try {
      Mysqlcon con = new Mysqlcon();
      con.connect();
      String queryString = String.format("DELETE FROM bookings WHERE booking_id = %d", offeringId);
      con.executeUpdate(queryString);
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}