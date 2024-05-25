import java.util.ArrayList;

public class Deliveryman extends LoginInformation{
    private String vehicleType;//car bicycle motorbicycle
    private  String gender;
    public Deliveryman(String fullName,String lastname,String id,String email,String phoneNumber,String location,String gender,String  vehicleType){
        super(fullName,id,email,phoneNumber,location);
        this.gender=gender;
        this.vehicleType=vehicleType;
    }

    /**
     * this is a getter and setter method
     * @param gender
     */
    void setGender(String gender){
        /*
        sql query using id
         */
    }

    public String getGender(String gender){
        /*
        sql query using id
         */
        return "";
    }

    /**
     *
     * @param vehicleType
     */


    void setVehicleType(String vehicleType){
        /*
        sql query using id
         */
    }

    public String getVehicleType(){
        /*
        sql query using id
         */
        return "";
    }



    /**
     * add and delete delivery man from the database
     */

    public void  addDeliveryMantoDB(){
        /*
        SQL query to  the data
         */
    }

    public static void getDeliverymanfromDB(){
        /*
        Sql delete query
         */
    }


}