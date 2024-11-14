package lessonbooking.services;

import java.util.ArrayList;

import lessonbooking.DAO.LocationsDAO;
import lessonbooking.DAO.OfferingsDAO;
import lessonbooking.models.Location;
import lessonbooking.models.Offering;

public class GuestService {
  protected OfferingsDAO offeringsCatalog;
  protected LocationsDAO locationsCatalog;

  public GuestService() {
    this.offeringsCatalog = new OfferingsDAO();
    this.locationsCatalog = new LocationsDAO();
  }

  public ArrayList<Location> viewLocations() {
    return this.locationsCatalog.getAll();
  }

  public ArrayList<Offering> viewOfferingsByLocation(Location location) {
    return this.offeringsCatalog.fetchByLocationId(location.getId());
  }

  public ArrayList<Offering> viewOfferingsByLessonType(String lessonType) {
    return this.offeringsCatalog.fetchByLessonType(lessonType);
  }

  public ArrayList<Offering> viewOfferingsByCity(String cityName) {
    return this.offeringsCatalog.fetchByCity(cityName);
  }

  public ArrayList<Offering> viewAllOfferings() {
    return this.offeringsCatalog.fetchAll();
  }
}