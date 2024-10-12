package sample.fulldoortodoordeliveryapp.SourceCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customer extends LoginInformation{
    private String gender;
    private ArrayList<Orders> order=new ArrayList<>();


    public Customer(){};
    public Customer(String firstName,String lastName ,String userName,String role,String password,String email,String phoneNumber,String location,float availableBalance,byte [] image,String gender){
        super(firstName, lastName ,userName,role,password, email,phoneNumber,location, availableBalance, image);
        this.gender=gender;
    }



    public ArrayList<Orders> getOrderfromDb(){
        this.setOrder(Orders.getOrderDB(this.getUserName()));
       return this.order;

    }

    public void addCustomer_ordersDb() {
        for(Orders ord: this.order){
            ord.addOrdertoDB();
        }
    }


    public ArrayList<Orders> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Orders> order) {
        this.order = order;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }





    /**
     * add  and delete customer form the database
     */


    public void  addCustomertoDB() {
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?,?,?)");
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
            stm.executeUpdate();

        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static Customer getCustomerfromDB(String userName) {
       Customer cust=new Customer();
       try{
           PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from customers where CUserName=?");
           stm.setString(1,userName);
           ResultSet result=stm.executeQuery();
                while(result.next()){
                   cust.setFirstName((result.getString(1)));
                   cust.setLastName((result.getString(2)));
                   cust.setUserName(result.getString(3));
                   cust.setRole(result.getString(4));
                   cust.setPassword(result.getString(5));
                   cust.setEmail(result.getString(6));
                   cust.setPhoneNumber(result.getString(7));
                   cust.setLocation(result.getString(8));
                   cust.setAvailableBallance(result.getFloat(9));
                   cust.setimage(result.getBytes(10));
                   cust.setGender(result.getString(11));
           }
       }
       catch (SQLException e){
           System.out.println(e);
       }
    return cust;
    }





    public static void  updateCustomerBallance(String CuserName ,Float amount) {
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("update Customers set AccountBalance=AccountBalance+?where CUserName=?");
            stm.setFloat(1,amount);
            stm.setString(2,CuserName);
            stm.executeUpdate();

        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

}
