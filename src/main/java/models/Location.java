package models;


public class Location {

    //Instance variables
    private int locationID;
    private String city;
    private String name;  //an example of name would be the EV building 
    private String address; 

    //Constructor
    public Location(int locationID, String city, String name, String address){
        this.locationID = locationID;
        this.city = city;
        this.name = name;
        this.address = address;
    }

    //Getters and setters
    public int getLocationID() {
        return locationID;
    }

    public void setLocationId(int locationID) {
        this.locationID = locationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /* Method to get available offerings
    public List<Schedule> getAvailableOfferings() {

    }
    */
}
