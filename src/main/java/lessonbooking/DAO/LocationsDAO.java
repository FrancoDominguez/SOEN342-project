package lessonbooking.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import lessonbooking.models.Location;
import lessonbooking.services.Mysqlcon;

public class LocationsDAO {

  public ArrayList<Location> getById(int id) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("SELECT * FROM locations WHERE id = '%d'", id);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    ArrayList<Location> newLocations = new ArrayList<Location>();
    while (rs.next()) {
      String name = rs.getString("name");
      String address = rs.getString("address");
      String city = rs.getString("city");
      int organizationId = rs.getInt("organization_id");
      Location newLocation = new Location(id, name, address, city, organizationId);
      newLocations.add(newLocation);
    }
    con.close();
    return newLocations;
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
