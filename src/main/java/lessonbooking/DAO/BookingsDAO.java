package lessonbooking.DAO;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lessonbooking.models.Booking;
import lessonbooking.services.Mysqlcon;

public class BookingsDAO {
  public BookingsDAO() {
  }

  public ArrayList<Booking> fetchByClientId(int clientId) throws Exception {
    ArrayList<Booking> bookings = new ArrayList<Booking>();
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "SELECT clients.username, clients.firstname, clients.lastname, " +
            "offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, offerings.is_available, " +
            "offerings.max_participants, offerings.participants, offerings.start_time, offerings.end_time, " +
            "locations.name AS location_name, locations.address, " +
            "instructors.firstname AS instructor_firstname, instructors.lastname AS instructor_lastname " +
            "FROM bookings " +
            "JOIN clients ON bookings.client_id = clients.id " +
            "JOIN offerings ON bookings.offering_id = offerings.id " +
            "JOIN locations ON offerings.location_id = locations.id " +
            "JOIN instructor_offering ON offerings.id = instructor_offering.offering_id " +
            "JOIN instructors ON instructor_offering.instructor_id = instructors.id " +
            "WHERE bookings.client_id = %d;",
        clientId);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    while (rs.next()) {
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
      LocalDateTime endTime = rs.getTimestamp("end_time") != null ? rs.getTimestamp("end_time").toLocalDateTime()
          : null;
      String locationName = rs.getString("location_name");
      String locationAddress = rs.getString("address");
      String instructorFirstname = rs.getString("instructor_firstname");
      String instructorLastname = rs.getString("instructor_lastname");

      Booking newBooking = new Booking(clientId, offeringId, clientUsername, clientFirstname, clientLastname,
          lessonType,
          privatePublic, isAvailable, maxParticipants, participants, startTime, endTime,
          locationName, locationAddress, instructorFirstname, instructorLastname);
      bookings.add(newBooking);
    }
    con.close();
    return bookings;
  }

  public void insert(int clientId, int offeringId) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("INSERT INTO bookings (client_id, offering_id) VALUES (%d, %d)",
        clientId, offeringId);
    con.executeUpdate(queryString);
    con.close();
  }

  public void delete(int clientId) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("DELETE FROM bookings WHERE client_id = %d", clientId);
    con.executeUpdate(queryString);
    con.close();
  }
}