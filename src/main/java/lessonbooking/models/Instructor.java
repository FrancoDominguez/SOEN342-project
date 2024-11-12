package lessonbooking.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Instructor extends Account {
  private String specialization;
  private ArrayList<String> city;
  private ArrayList<Offering> offerings;

  public Instructor(String firstname, String lastname, String phoneNumber, String password, LocalDate dateofBirth, String specialization){
    super(firstname, lastname, phoneNumber, password, dateOfBirth);
    this.specialization = specialization;
  }
    //this.city = city;
    //this.offerings = new ArrayList<>();
  

  public String getSpecialization() {
    return this.specialization;
  }

  public ArrayList<Offering> getOfferings() {
    return this.offerings;
  }

  public void takeOffering(Offering offering) {
    try {
      offering.assignInstructor(this);
      this.offerings.add(offering);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public String toString() {
    String stringData = String.format("id: %s, firstname: %s, lastname: %s, phoneNumber: %s, specialization: %s",
        this.id,
        this.firstname, this.lastname, this.phoneNumber, specialization);
    return stringData;
  }
}