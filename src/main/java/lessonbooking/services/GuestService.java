package lessonbooking.services;

import java.util.ArrayList;

import lessonbooking.DAO.LocationsDAO;
import lessonbooking.DAO.OfferingsDAO;
import lessonbooking.DAO.OrganizationsDAO;
import lessonbooking.models.Location;
import lessonbooking.models.Offering;
import lessonbooking.models.Organization;

public class GuestService {
  protected OfferingsDAO offeringsCatalog;
  protected LocationsDAO locationsCatalog;
  protected OrganizationsDAO organizationsCatalog;

  public GuestService() {
    this.offeringsCatalog = new OfferingsDAO();
    this.locationsCatalog = new LocationsDAO();
    this.organizationsCatalog = new OrganizationsDAO();
  }

  private ArrayList<Offering> filter(ArrayList<Offering> offerings) {
    if (this instanceof ClientService) {
      ArrayList<Offering> filteredOfferings = new ArrayList<Offering>();
      for (int i = 0; i < offerings.size(); i++) {
        Offering offering = offerings.get(i);
        if (offering.isAvailable()) {
          filteredOfferings.add(offering);
        }
      }
      return filteredOfferings;
    } else {
      return offerings;
    }
  }

  // tested
  public ArrayList<Location> viewLocations() {
    return this.locationsCatalog.getAll();
  }

  public ArrayList<Offering> viewEmptyOfferings() {
    ArrayList<Offering> offerings = this.offeringsCatalog.fetchAll();
    ArrayList<Offering> filteredOfferings = new ArrayList<Offering>();
    for (int i = 0; i < offerings.size(); i++) {
      Offering offering = offerings.get(i);
      if (offering.isEmpty()) {
        filteredOfferings.add(offering);
      }
    }
    return filteredOfferings;
  }

  //
  public ArrayList<Offering> viewOfferingsByLocation(Location location) {
    // add filtering for fully booked
    return this.offeringsCatalog.fetchByLocationId(location.getId());
  }

  // tested
  public ArrayList<Offering> viewOfferingsByLessonType(String lessonType) {
    return this.offeringsCatalog.fetchByLessonType(lessonType);
  }

  // tested
  public ArrayList<Offering> viewOfferingsByCity(String cityName) {
    return this.offeringsCatalog.fetchByCity(cityName);
  }

  // tested
  public ArrayList<Offering> viewAllOfferings() {
    return this.offeringsCatalog.fetchAll();
  }

  // tested
  public ArrayList<Organization> viewOrganizations() {
    return this.organizationsCatalog.fetchAll();
  }
}