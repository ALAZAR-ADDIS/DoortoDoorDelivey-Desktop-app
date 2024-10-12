package sample.fulldoortodoordeliveryapp.SourceCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Deliveryman extends LoginInformation{


    private byte[] DeliveryManIdImage;
    private String vehicleType;
    private  String gender;
    Deliveryman(){}
    public Deliveryman(String firstName,String lastName ,String userName,String role,String password,String email,String phoneNumber,String location,float availableBalance,byte [] image,String gender,String vehicleType,byte[] deliveryManIdImage){
        super(firstName, lastName ,userName,role,password, email,phoneNumber,location, availableBalance, image);
        this.gender=gender;
        this.vehicleType=vehicleType;
        this.DeliveryManIdImage=deliveryManIdImage;
    }

    public byte[] getDeliveryManIdImage() {
        return DeliveryManIdImage;
    }

    public void setDeliveryManIdImage(byte[] deliveryManIdImage) {
        DeliveryManIdImage = deliveryManIdImage;
    }
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public void  addDeliveryMantoDB(){
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("insert into DeliveryMans values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1,super.getFirstName());
            stm.setString(2,super.getLastName());
            stm.setString(3,super.getUserName());
            stm.setString(4,super.getRole());
            stm.setString(5,super.getPassword());
            stm.setString(6,super.getEmail());
            stm.setString(7,super.getPhoneNumber());
            stm.setString(8,super.getLocation());
            stm.setFloat(9,super.getAvailableBallace());
            stm.setBytes(10,super.getimage());
            stm.setString(11,this.getGender());
            stm.setString(12,this.vehicleType);
            stm.setBytes(13,this.DeliveryManIdImage);
            stm.executeUpdate();

        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static Deliveryman getDeliverymanfromDB(String userName){
        Deliveryman DM=new Deliveryman();
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from Deliverymans where DMUserName=?");
            stm.setString(1,userName);
            ResultSet result=stm.executeQuery();
            while(result.next()){
                DM.setFirstName((result.getString(1)));
                DM.setLastName((result.getString(2)));
                DM.setUserName(result.getString(3));
                DM.setRole(result.getString(4));
                DM.setPassword(result.getString(5));
                DM.setEmail(result.getString(6));
                DM.setPhoneNumber(result.getString(7));
                DM.setLocation(result.getString(8));
                DM.setAvailableBallance(result.getFloat(9));
                DM.setimage(result.getBytes(10));
                DM.setGender(result.getString(11));
                DM.setVehicleType(result.getString(12));
                DM.setDeliveryManIdImage(result.getBytes(13));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return DM;

    }


}