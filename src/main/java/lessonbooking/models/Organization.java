package lessonbooking.models;

import java.util.ArrayList;

public class Organization {
  private int id;
  private String name;
  private ArrayList<Location> locations;

  public Organization(String name) {
    this.id = -1;
    this.name = name;
    this.locations = new ArrayList<Location>();
  }

  public Organization(int id, String name) {
    this.id = id;
    this.name = name;
    this.locations = new ArrayList<Location>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Location> getLocations() {
    return locations;
  }
}