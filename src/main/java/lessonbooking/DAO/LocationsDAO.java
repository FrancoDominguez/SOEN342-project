package lessonbooking.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import lessonbooking.models.Location;
import lessonbooking.services.Mysqlcon;

public class LocationsDAO {

  public ArrayList<Location> getAll() {
    Mysqlcon con = new Mysqlcon();
    ArrayList<Location> locations = new ArrayList<Location>();
    try {
      con.connect();
      String queryString = String.format("SELECT * FROM locations");
      con.executeQuery(queryString);
      ResultSet rs = con.getResultSet();
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String city = rs.getString("city");
        int organizationId = rs.getInt("organization_id");
        Location location = new Location(id, name, address, city, organizationId);
        locations.add(location);
      }
      con.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return locations;
  }

  public Location getByOrganizationId() {
    // implement this
    return null;
  }

  public void insert(Location location) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format(
        "INSERT INTO locations (name, address, city, organization_id) VALUES ('%s', '%s','%s','%d')",
        location.getName(), location.getAddress(), location.getCity(), location.getOrganizationId());
    con.executeUpdate(queryString);
  }
}
