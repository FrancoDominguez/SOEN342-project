package main.java.models;

import java.util.ArrayList;
import java.util.UUID;

public class Organization {
  private String organizationId;
  private String name;
  private ArrayList<Location> locations;

  public Organization(String name) {
    this.organizationId = UUID.randomUUID().toString();
    this.name = name;
    this.locations = new ArrayList<Location>();
  }

  public void addLocation(Location location) {
    this.locations.add(location);
  }
}