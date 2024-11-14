package lessonbooking.models;

import java.util.ArrayList;
import java.util.UUID;

public class Organization {
  private int id;
  private String name;
  private ArrayList<Location> locations;

  public Organization(String name) {
    this.id = -1;
    this.name = name;
    this.locations = new ArrayList<Location>();
  }

  public void addLocation(Location location) {
    this.locations.add(location);
  }
}