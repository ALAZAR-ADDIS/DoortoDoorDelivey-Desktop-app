package sample.fulldoortodoordeliveryapp.SourceCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static sample.fulldoortodoordeliveryapp.SourceCode.DatabaseConnection.con;

public class Cart {
    private String CUsername;
    private String Name;
    private int Amount;
    private Float Price;
    private String productId;
    public Cart(){}
    public Cart(String CUsername, String name, int amount, Float price, String productId) {
        this.CUsername = CUsername;
        this.Name = name;
        this.Amount = amount;
        this.Price = price;
        this.productId = productId;
    }

    public String getCUsername() {
        return this.CUsername;
    }

    public void setCUsername(String CUsername) {
        this.CUsername = CUsername;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getAmount() {
        return this.Amount;
    }

    public void setAmount(int amount) {
        this.Amount = amount;
    }

    public Float getPrice() {
        return this.Price;
    }

    public void setPrice(Float price) {
        this.Price = price;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void addProductToCartDb() {

        try {
            PreparedStatement stm = con.prepareStatement("insert into cart values(?,?,?,?,?)");
            stm.setString(1, this.getCUsername());
            stm.setString(2, this.getName());
            stm.setInt(3, this.getAmount());
            stm.setFloat(4, this.getPrice());
            stm.setString(5, this.getProductId());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static ArrayList<Cart> getCartDb( String CUsername){

        ArrayList<Cart> CartDb=new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("Select *from cart where CUserName=?");
            stm.setString(1,CUsername);
            ResultSet result=stm.executeQuery();
            while(result.next()){
                Cart cart=new Cart();
                cart.setCUsername(result.getString(1));
                cart.setName(result.getString(2));
                cart.setAmount(result.getInt(3));
                cart.setPrice(result.getFloat(4));
                cart.setProductId(result.getString(5));
                CartDb.add(cart);
            }



        }
        catch(SQLException e){
            System.out.println(e);
        }

        return CartDb;
    }

    public static void DeleteCartDb(String ProductId,String CUsername){
        try {
            PreparedStatement stm = con.prepareStatement("delete cart where ProductId=? and CUserName=?");
            stm.setString(1,ProductId);
            stm.setString(2,CUsername);
            stm.executeUpdate();

        }
        catch(SQLException e){
            System.out.println(e);
        }

    }


}

