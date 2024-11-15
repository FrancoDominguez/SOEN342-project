package lessonbooking.services;

import java.time.LocalDate;
import java.util.ArrayList;

import lessonbooking.DAO.AdministratorsDAO;
import lessonbooking.DAO.ClientsDAO;
import lessonbooking.DAO.InstructorsDAO;
import lessonbooking.models.Administrator;
import lessonbooking.models.Client;
import lessonbooking.models.Instructor;

public class RegistrationService {
  private ClientsDAO clientsCatalog;
  private InstructorsDAO instructorsCatalog;
  private AdministratorsDAO administratorsCatalog;

  public RegistrationService() {
    this.clientsCatalog = new ClientsDAO();
    this.instructorsCatalog = new InstructorsDAO();
    this.administratorsCatalog = new AdministratorsDAO();
  }

  public void registerClient(String username, String firstname, String lastname, String phoneNumber,
      String password, LocalDate dateOfBirth) throws Exception {
    if (clientsCatalog.fetchByUsername(username) != null) {
      throw new Exception("User already exists");
    } else {
      Client newClient = new Client(username, firstname, lastname, phoneNumber, password, dateOfBirth);
      clientsCatalog.insert(newClient);
    }
  }

  public void registerInstructor(String username, String firstname, String lastname, String phoneNumber,
      String password, LocalDate dateOfBirth, String specialization, ArrayList<String> cities) throws Exception {
    if (instructorsCatalog.fetchByUsername(username) != null) {
      throw new Exception("User already exists");
    } else {
      Instructor newInstructor = new Instructor(username, firstname, lastname, phoneNumber, password,
          dateOfBirth, specialization, cities);
      instructorsCatalog.insert(newInstructor);
    }
  }

  public void registerAdministrator(String username, String firstname, String lastname, String phoneNumber,
      String password,
      LocalDate dateOfBirth) throws Exception {
    if (administratorsCatalog.fetchByUsername(username) != null) {
      throw new Exception("User already exists");
    } else {
      Administrator newAdmin = new Administrator(username, firstname, lastname, phoneNumber, password, dateOfBirth);
      administratorsCatalog.insert(newAdmin);
    }
  }
}
