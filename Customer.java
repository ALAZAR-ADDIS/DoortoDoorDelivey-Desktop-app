import java.util.ArrayList;

public class Customer extends LoginInformation{
    private String gender;
    public Customer(String fullName,String id,String email,String phoneNumber,String location,String age){
       super(fullName,id,email,phoneNumber,location);
        this.gender=gender;
    }

    /**
     * this is a getter and setter method for gender
     * @param gender
     */
    void setGender(String gender){
         /*
        SQL query to  the data
         */
    }

    public String getGender(String gender){
         /*
        SQL query to  the data
         */
        return "";
    }






    /**
     * add  and delete customer form the database
     */

    public void  addCustomertoDB(){
        /*
        SQL query to  the data
         */
    }

    public static void getCustomertoDB() {
        /*
        Sql delete query by id
         */
    }





}
