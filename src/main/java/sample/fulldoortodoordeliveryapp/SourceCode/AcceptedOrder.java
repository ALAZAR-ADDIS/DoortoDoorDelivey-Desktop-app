package sample.fulldoortodoordeliveryapp.SourceCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcceptedOrder {

    private String AorderCustomerName;
    private String AorderCustomerPno;
    private String ASuperMarketName;
    private String AorderSuperMarketLocation;
    private String AorderSuperMarkerPno;
    private String AOrderProducId;
    private String AorderCUserName;
    private int AorderAmount;
    private byte[] AorderImage;


    public String getAorderCUserName() {
        return AorderCUserName;
    }

    public void setAorderCUserName(String aorderCUserName) {
        AorderCUserName = aorderCUserName;
    }

    private String AorderTimeofPurchase;


    public String getAOrderProducId() {
        return AOrderProducId;
    }

    public void setAOrderProducId(String AOrderProducId) {
        this.AOrderProducId = AOrderProducId;
    }

    public String getASuperMarketName() {
        return ASuperMarketName;
    }

    public void setASuperMarketName(String ASuperMarketName) {
        this.ASuperMarketName = ASuperMarketName;
    }

    public int getAorderAmount() {
        return AorderAmount;
    }

    public void setAorderAmount(int aorderAmount) {
        AorderAmount = aorderAmount;
    }

    public String getAorderCustomerName() {
        return AorderCustomerName;
    }

    public void setAorderCustomerName(String aorderCustomerName) {
        AorderCustomerName = aorderCustomerName;
    }

    public String getAorderCustomerPno() {
        return AorderCustomerPno;
    }

    public void setAorderCustomerPno(String aorderCustomerPno) {
        AorderCustomerPno = aorderCustomerPno;
    }

    public byte[] getAorderImage() {
        return AorderImage;
    }

    public void setAorderImage(byte[] aorderImage) {
        AorderImage = aorderImage;
    }

    public String getAorderSuperMarkerPno() {
        return AorderSuperMarkerPno;
    }

    public void setAorderSuperMarkerPno(String aorderSuperMarkerPno) {
        AorderSuperMarkerPno = aorderSuperMarkerPno;
    }

    public String getAorderSuperMarketLocation() {
        return AorderSuperMarketLocation;
    }

    public void setAorderSuperMarketLocation(String aorderSuperMarketLocation) {
        AorderSuperMarketLocation = aorderSuperMarketLocation;
    }

    public String getAorderTimeofPurchase() {
        return AorderTimeofPurchase;
    }

    public void setAorderTimeofPurchase(String aorderTimeofPurchase) {
        AorderTimeofPurchase = aorderTimeofPurchase;
    }

    public AcceptedOrder(){}

    public AcceptedOrder(String aorderCustomerName, String aorderCustomerPno, String ASuperMarketName, String aorderSuperMarketLocation, String aorderSuperMarkerPno, String AOrderProducId, int aorderAmount, byte[] aorderImage, String aorderTimeofPurchase) {
        this.AorderCustomerName = aorderCustomerName;
        this.AorderCustomerPno = aorderCustomerPno;
        this.ASuperMarketName = ASuperMarketName;
        AorderSuperMarketLocation = aorderSuperMarketLocation;
        AorderSuperMarkerPno = aorderSuperMarkerPno;
        this.AOrderProducId = AOrderProducId;
        AorderAmount = aorderAmount;
        AorderImage = aorderImage;
        AorderTimeofPurchase = aorderTimeofPurchase;
    }

    public static ArrayList<AcceptedOrder> getAcceptedDB(String userName) {
        ArrayList<AcceptedOrder> AcceptedOrderList=new ArrayList<>();
        try{
            PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from AcceptedOrdersInfo where DMUserName=?");
            stm.setString(1,userName);
            ResultSet result=stm.executeQuery();
            while(result.next()){
                AcceptedOrder AO=new AcceptedOrder();
                AO.setAorderCustomerName((result.getString(1)+ result.getString(2)));
                AO.setAorderCustomerPno(result.getString(3));
                AO.setASuperMarketName(result.getString(4));
                AO.setAorderSuperMarketLocation(result.getString(5));
                AO.setAorderSuperMarkerPno(result.getString(6));
                AO.setAOrderProducId(result.getString(7));
                AO.setAorderAmount(result.getInt(8));
                AO.setAorderImage(result.getBytes(9));
                AO.setAorderTimeofPurchase(result.getString(10));
                AO.setAorderCUserName(result.getString(11));
              AcceptedOrderList.add(AO);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return AcceptedOrderList;
    }

}
