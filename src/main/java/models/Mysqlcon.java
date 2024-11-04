package models;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class Mysqlcon {
  public void connect() {
    try {
      Dotenv dotenv = Dotenv.load();
      String url = dotenv.get("AWS_MYSQL_ENDPOINT");
      String user = dotenv.get("AWS_MYSQL_USER");
      String password = dotenv.get("AWS_MYSQL_PASSWORD");
      System.out.println("url:" + url + "\n user: " + user + "\n password: " + password);

      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          url, user, password);
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from emp");
      while (rs.next())
        System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
      con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
