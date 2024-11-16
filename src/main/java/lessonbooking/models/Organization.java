package lessonbooking.models;

public class Organization {
  private int id;
  private String name;

  public Organization(String name) {
    this.id = -1;
    this.name = name;
  }

  public Organization(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Organization{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}