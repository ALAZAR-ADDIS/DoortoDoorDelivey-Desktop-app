package sample.fulldoortodoordeliveryapp.SourceCode;

import java.io.BufferedOutputStream;
import java.nio.Buffer;
import java.sql.*;

public class LoginInformation implements Getter_Setter{
    //database connection


    //instance variables of Logininformation class
    private String firstName;
    private String lastName;
    private String userName;
    private String  role;
    private String password;
    private String email;
    private String phoneNumber;
    private String location;
    private float  AvailableBalance;
    private byte[] image;


    //LoginInformation class constructor
    public LoginInformation(){}
    public LoginInformation(String firstName,String lastName ,String userName,String role,String password,String email,String phoneNumber,String location,float availableBalance,byte [] image){

      //database connection

        this.AvailableBalance=availableBalance;
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.role=role;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.location=location;
        this.image=image;
    }




    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String FirstName) {
      this.firstName=FirstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String LastName) {
            this.lastName=LastName;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public void setUserName(String UserName) {
        this.userName=UserName;

    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
            this.email=email;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(String location) {
        this.location=location;
    }

    @Override
    public float getAvailableBallace() {
        return this.AvailableBalance;
    }

    @Override
    public void setAvailableBallance(float availableBallance) {
            this.AvailableBalance=availableBallance;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
         this.password=password;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public void setRole(String role) {
          this.role=role;
    }
    @Override
    public void setimage(byte [] image){
        this.image= image;
    }

    @Override
   public byte [] getimage(){
        return this.image;
    }







}
