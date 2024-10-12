package sample.fulldoortodoordeliveryapp.SourceCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Orders {
    private String CUserName;
    private String ProductId;
    private String pickUpLocation;
    private String TimeOfPurchase;
    private String orderstatus;
    private int AmountofProduct;
    private byte [] invoiceImage;

    public Orders() {
    }
    public Orders(String CUserName, String productId, String pickUpLocation, String timeOfPurchase, String orderstatus, int amountofProduct) {
        this.CUserName = CUserName;
        this.ProductId = productId;
        this.pickUpLocation = pickUpLocation;
        this.TimeOfPurchase = timeOfPurchase;
        this.orderstatus = orderstatus;
        this.AmountofProduct = amountofProduct;

    }

    public byte[] getInvoiceImage() {
        return invoiceImage;
    }

    public void setInvoiceImage(byte[] invoiceImage) {
        this.invoiceImage = invoiceImage;
    }

    public String getCUserName() {
        return this.CUserName;
    }

    public void setCUserName(String CUserName) {
        this.CUserName = CUserName;
    }

    public String getProductId() {
        return this.ProductId;
    }

    public void setProductId(String productId) {
        this.ProductId = productId;
    }

    public String getPickUpLocation() {
        return this.pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getTimeOfPurchase() {
        return this.TimeOfPurchase;
    }

    public void setTimeOfPurchase(String timeOfPurchase) {
        TimeOfPurchase = timeOfPurchase;
    }

    public String getOrderstatus() {
        return this.orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public int getAmountofProduct() {
        return this.AmountofProduct;
    }

    public void setAmountofProduct(int amountofProduct) {
        AmountofProduct = amountofProduct;
    }


    public void addOrdertoDB() {
        try {
            PreparedStatement stm = DatabaseConnection.con.prepareStatement("insert into orders(CUserName,Productid,PickUplocation,TimeofPurchase,OrderStatus,Amount) values(?,?,?,?,?,?)");
            stm.setString(1, this.getCUserName());
            stm.setString(2, this.getProductId());
            stm.setString(3, this.getPickUpLocation());
            stm.setString(4, this.getTimeOfPurchase());
            stm.setString(5, this.getOrderstatus());
            stm.setInt(6, this.getAmountofProduct());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Orders> getOrderDB(String CUserName) {
        ArrayList<Orders> ordersDB = new ArrayList<>();

        try {
            PreparedStatement stm = DatabaseConnection.con.prepareStatement("Select * from orders where CUserName=? ");
            stm.setString(1, CUserName);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Orders ord = new Orders();
                ord.setCUserName(result.getString(1));
                ord.setProductId(result.getString(2));
                ord.setPickUpLocation(result.getString(3));
                ord.setTimeOfPurchase(result.getString(4));
                ord.setOrderstatus(result.getString(5));
                ord.setAmountofProduct(result.getInt(6));
                ord.setInvoiceImage(result.getBytes(7));
                ordersDB.add(ord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ordersDB;
    }


    public static ArrayList<Orders> getOrderDB() {
        ArrayList<Orders> ordersDB = new ArrayList<>();

        try {
            PreparedStatement stm = DatabaseConnection.con.prepareStatement("Select * from orders");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Orders ord = new Orders();
                ord.setCUserName(result.getString(1));
                ord.setProductId(result.getString(2));
                ord.setPickUpLocation(result.getString(3));
                ord.setTimeOfPurchase(result.getString(4));
                ord.setOrderstatus(result.getString(5));
                ord.setAmountofProduct(result.getInt(6));
                ordersDB.add(ord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ordersDB;
    }


    public static void SetOrderStatus(String Status,String CuserName,String TimeOfPurchase){
        try {
            System.out.println("SetOrderStatus///////////////////");
            PreparedStatement stm = DatabaseConnection.con.prepareStatement("Update orders  set orderStatus=? where CUserName=? and TimeofPurchase=?");
            stm.setString(1, Status);
            stm.setString(2,CuserName );
            stm.setString(3, TimeOfPurchase);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void SetOrderPickedBy(String CuserName,String TimeOfPurchase,String DMuserName,String ProductId){
        try {
            System.out.println("SetOrderStatus///////////////////");
            PreparedStatement stm = DatabaseConnection.con.prepareStatement("insert into OrderPickedBy(ProductId,CUserName,TimeOfPurchase,DMuserName) values(?,?,?,?)");
            stm.setString(1, ProductId);
            stm.setString(2,CuserName );
            stm.setString(3, TimeOfPurchase);
            stm.setString(4, DMuserName);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }



    public static void addInvoiceTODB(byte[]image,String Cusername,String timeOfPerchase) {
        try {
            PreparedStatement stm = DatabaseConnection.con.prepareStatement("update orders set invoice= ?where CuserName=? and Timeofpurchase=?");
            stm.setBytes(1, image);
            stm.setString(2, Cusername);
            stm.setString(3, timeOfPerchase);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }






    public static ArrayList<Orders> getincomingorders(String SuserNames) {
        ArrayList<Orders> ordersDB = new ArrayList<>();

        try {
            PreparedStatement stm = DatabaseConnection.con.prepareStatement("Select * from IncomingOrders where SuserName=?");
            stm.setString(1,SuserNames);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Orders ord = new Orders();
                ord.setCUserName(result.getString(1));
                ord.setProductId(result.getString(2));
                ord.setPickUpLocation(result.getString(3));
                ord.setTimeOfPurchase(result.getString(4));
                ord.setOrderstatus(result.getString(5));
                ord.setAmountofProduct(result.getInt(6));
                ordersDB.add(ord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ordersDB;
    }

}