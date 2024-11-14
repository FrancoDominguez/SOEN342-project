package lessonbooking.services;

import lessonbooking.DAO.InstructorOfferingsDAO;
import lessonbooking.DAO.InstructorsDAO;
import lessonbooking.models.Instructor;
import lessonbooking.models.Offering;

public class InstructorService extends GuestService {
  private Instructor instructor;
  private InstructorsDAO instructorsCatalog;
  private InstructorOfferingsDAO instructorOfferingsCatalog;

  public InstructorService() {
    super();
    this.instructorsCatalog = new InstructorsDAO();
    this.instructorOfferingsCatalog = new InstructorOfferingsDAO();
  }

  public void login(String username, String password) throws Exception {
    Instructor instructor = instructorsCatalog.fetchByUsername(username);
    if (instructor == null) {
      throw new Exception(String.format("Instructor account with username '%s' was not found", username));
    } else if (!instructor.validatePassword(password)) {
      throw new Exception("Incorrect password");
    } else {
      this.instructor = instructor;
    }
  }

  public void takeOffering(Offering offering) throws Exception {
    if (offering.hasInstructor()) {
      throw new Exception("This offering already has an instructor assigned to it");
    } else if (!this.instructor.hasCity(offering.getLocationCity())) {
      throw new Exception(
          String.format("Unable to take offering: Instructor not available in city '%s'", offering.getLocationCity()));
    } else if (instructor.isSpecializedIn(offering.getLessonType())) {
      throw new Exception(String.format("Unable to take offering: Instructor is not qualified to teach '%s'",
          offering.getLessonType()));
    } else {
      offering.assignInstructor(instructor);
      this.offeringsCatalog.save(offering);
      this.instructorOfferingsCatalog.insert(this.instructor.getId(), offering.getId());
    }
  }

  public void dropOffering(Offering offering) throws Exception {
    if (!offering.isEmpty()) {
      throw new Exception("Cannot drop offering with active participants");
    } else {
      offering.dropInstructor();
      this.offeringsCatalog.save(offering);
      this.instructorOfferingsCatalog.delete(offering.getId());
    }
  }
}