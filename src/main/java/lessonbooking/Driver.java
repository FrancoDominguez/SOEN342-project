package lessonbooking;

import java.sql.ResultSet;

import lessonbooking.services.Mysqlcon;

public class Driver {
  public static void main(String[] args) {
    testDB();
    System.out.println("Welcome to the Lesson Booking System!");
  }

  public static void testDB() {
    try {
      Mysqlcon connection = new Mysqlcon();
      connection.connect();
      connection.executeQuery("SELECT * FROM test_table;");
      ResultSet result = connection.getResultSet();
      while (result.next()) {
        System.out.println(result.getString("name"));
      }
      connection.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
