package lessonbooking.models;

public class Location {
  private int id;
  private String name;
  private String address;
  private String city;
  private int organizationid;

  public Location(int id, String name, String address, String city, int organizationId) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.city = city;
    this.organizationid = organizationId;
  }

  public Location(String name, String address, String city, int organizationId) {
    this.id = -1;
    this.name = name;
    this.address = address;
    this.city = city;
    this.organizationid = organizationId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public int getOrganizationId() {
    return this.organizationid;
  }
}
