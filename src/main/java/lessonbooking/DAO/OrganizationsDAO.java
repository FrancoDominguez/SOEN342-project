package lessonbooking.DAO;

import java.sql.ResultSet;

import lessonbooking.models.Organization;
import lessonbooking.services.Mysqlcon;

public class OrganizationsDAO {

  public Organization fetchByName(String name) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("SELECT * FROM organizations WHERE name = %s", name);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    Organization newOrganization;
    if (rs.next()) {
      int id = rs.getInt("id");
      newOrganization = new Organization(id, name);
      con.close();
      return newOrganization;
    }
    con.close();
    return null;
  }

  public Organization fetchById(int id) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("SELECT * FROM organizations WHERE id = %d", id);
    con.executeQuery(queryString);
    ResultSet rs = con.getResultSet();
    Organization newOrganization;
    if (rs.next()) {
      String name = rs.getString("name");
      newOrganization = new Organization(id, name);
      con.close();
      return newOrganization;
    }
    con.close();
    return null;
  }

  public void insert(Organization organization) throws Exception {
    Mysqlcon con = new Mysqlcon();
    con.connect();
    String queryString = String.format("INSERT INTO organizations (id, name) VALUES (%d, '%s')", organization.getId(),
        organization.getName());
    con.executeUpdate(queryString);
    con.close();
  }
}
