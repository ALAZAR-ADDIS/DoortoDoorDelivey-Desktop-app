package sample.fulldoortodoordeliveryapp.SourceCode;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuperMarket extends LoginInformation {
     private  product products=new product();

    public product getProducts() {
        return products;
    }

    public void setProducts(product products) {
        this.products = products;
    }

    public SuperMarket(){}
    public SuperMarket(String firstName,String lastName ,String userName,String role,String password,String email,String phoneNumber,String location,float availableBalance,byte [] image){
        super(firstName, lastName ,userName,role,password, email,phoneNumber,location, availableBalance, image);

    }

    public void deleteProductfromDb(){
        this.products.deleteProduct();
    }
    public void addProductstoDb(){

        this.products.addProductDB(this.getUserName());
    }

    public void updateProducts(){
        this.products.updateProductDB();
    }
    public ArrayList<product> getProductFromDb(){
        ArrayList<product> thisSupeMrketProducts=new ArrayList<>();
         for(product p:  product.getProductDb()){
             if(p.getSuserName().equals(this.getUserName())){
                 thisSupeMrketProducts.add(p);
             }

         }
        return thisSupeMrketProducts;
    }
    public ArrayList<product> getNoticeProductFromDb(){

        return product.getNoticProductsDb(this.getUserName());
    }




    public void  addSuperMarkertoDB() {
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("insert into SuperMarkets values(?,?,?,?,?,?,?,?,?)");
            stm.setString(1,super.getFirstName());
            stm.setString(2,super.getUserName());
            stm.setString(3,super.getRole());
            stm.setString(4,super.getPassword());
            stm.setString(5,super.getEmail());
            stm.setString(6,super.getPhoneNumber());
            stm.setString(7,super.getLocation());
            stm.setFloat(8,super.getAvailableBallace());
            stm.setBytes(9,super.getimage());
            stm.executeUpdate();

        }
        catch (SQLException e){
            System.out.println(e);
        }
    }


    public static SuperMarket getSuperMarkettoDB(String userName){
        SuperMarket SM=new SuperMarket();
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from SuperMarkets where SUserName=?");
            stm.setString(1,userName);
            ResultSet result=stm.executeQuery();
            while(result.next()){
                SM.setFirstName((result.getString(1)));
                SM.setUserName(result.getString(2));
                SM.setRole(result.getString(3));
                SM.setPassword(result.getString(4));
                SM.setEmail(result.getString(5));
                SM.setPhoneNumber(result.getString(6));
                SM.setLocation(result.getString(7));
                SM.setAvailableBallance(result.getFloat(8));
                SM.setimage(result.getBytes(9));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        System.out.println(SM.getFirstName());
        return SM;
    }





}


