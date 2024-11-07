package models;

public class Administrator {
    
    //Instance Variable
    String name;

    //Default admin constructor
    public Administrator(){
        name = "Admin";
    }

    //Method for admin to delete an account
    public void deleteAccount(Account account){
    
    }

    //Method for admin to create an Offering
    public void createOffering(Offering offering){

    }

    //ToString Method for admin to view all records
    public String viewAllRecords(){
        return "Records: ...";
    }

}
