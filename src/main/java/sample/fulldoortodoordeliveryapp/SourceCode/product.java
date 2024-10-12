package sample.fulldoortodoordeliveryapp.SourceCode;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class product{
    public String SuserName;
    private String productName;
    private String productDescription;
    private String productId;
    private int numberAvailable;
    private int productSoldOut;
    private float price;
    private  byte[] productImage;


    //product constructor
    public product(){}
    public product(String suserName, String productName, String productDescription, String productId, int numberAvailable, int productSoldOut, float price, byte[] productImage) {
        SuserName = suserName;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productId = productId;
        this.numberAvailable = numberAvailable;
        this.productSoldOut = productSoldOut;
        this.price = price;
        this.productImage = productImage;
    }

    /**
     * getter and setter
     *
     */
    public String getSuserName() {
        return this.SuserName;
    }

    public void setSuserName(String suserName) {
        this.SuserName = suserName;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getNumberAvailable() {
        return this.numberAvailable;
    }

    public void setNumberAvailable(int numberAvailable) {
        this.numberAvailable = numberAvailable;
    }

    public int getProductSoldOut() {
        return this.productSoldOut;
    }

    public void setProductSoldOut(int productSoldOut) {
        this.productSoldOut = productSoldOut;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getProductImage() {
        return this.productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }


    public void deleteProduct(){
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("delete Product where productId =?");
            stm.setString(1,this.productId);
            stm.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }

    public  void addProductDB(String SUserName){
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("insert into product values(?,?,?,?,?,?,?,?)");
            stm.setString(1, SUserName);
            stm.setString(2, this.getProductName());
            stm.setString(3,this.getProductDescription());
            stm.setString(4,this.getProductId());
            stm.setInt(5,this.getNumberAvailable());
            stm.setInt(6,this.getProductSoldOut());
            stm.setFloat(7,this.getPrice());
            stm.setBytes(8,this.getProductImage());
            stm.executeUpdate();

        }
        catch (SQLException e){
            System.out.println(e);
        }

    }

    public  void updateProductDB( ){
        this.deleteProduct();
        this.addProductDB( this.SuserName);
    }



    public static ArrayList<product> getProductDb(){

        ArrayList<product> productsDB=new ArrayList<>();

        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from product ");
            ResultSet result=stm.executeQuery();
            while(result.next()){
                product pro=new product();
                pro.setSuserName(result.getString(1));
                pro.setProductName((result.getString(2)));
                pro.setProductDescription((result.getString(3)));
                pro.setProductId(result.getString(4));
                pro.setNumberAvailable(result.getInt(5));
                pro.setProductSoldOut(result.getInt(6));
                pro.setPrice(result.getFloat(7));
                pro.setProductImage(result.getBytes(8));
                productsDB.add(pro);

            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return productsDB;

    }


    public static ArrayList<product> getNoticProductsDb(String SuserName){
        ArrayList<product> productsDB=new ArrayList<>();

        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from product where NumberProductAvailable<5 and SuserName=?");
            stm.setString(1,SuserName);
            ResultSet result=stm.executeQuery();
            while(result.next()){
                product pro=new product();
                pro.setSuserName(result.getString(1));
                pro.setProductName((result.getString(2)));
                pro.setProductDescription((result.getString(3)));
                pro.setProductId(result.getString(4));
                pro.setNumberAvailable(result.getInt(5));
                pro.setProductSoldOut(result.getInt(6));
                pro.setPrice(result.getFloat(7));
                pro.setProductImage(result.getBytes(8));
                productsDB.add(pro);

            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return productsDB;

    }




    public static ArrayList<product> SerchProduct(String ProductName){

        ArrayList<product> productsDB=new ArrayList<>();

        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from product  where ProductName like ? or  ProductName like ?  or ProductName like ? ");
            stm.setString(1,"%"+ProductName+"%");
            stm.setString(2,ProductName+"%");
            stm.setString(3,"%"+ProductName);
            ResultSet result=stm.executeQuery();

            while(result.next()){
                product pro=new product();

                pro.setSuserName(result.getString(1));
                pro.setProductName((result.getString(2)));
                pro.setProductDescription((result.getString(3)));
                pro.setProductId(result.getString(4));
                pro.setNumberAvailable(result.getInt(5));
                pro.setProductSoldOut(result.getInt(6));
                pro.setPrice(result.getFloat(7));
                pro.setProductImage(result.getBytes(8));
                System.out.println(pro.getProductName()+"////////////////////");
                productsDB.add(pro);

            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return productsDB;

    }






}