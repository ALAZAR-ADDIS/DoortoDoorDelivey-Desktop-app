package sample.fulldoortodoordeliveryapp.SourceCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static sample.fulldoortodoordeliveryapp.SourceCode.DatabaseConnection.con;

public class ServiceProvider {


    private  float payableBallance;//only the delivery man  service charge
    private float AvailableBalance;//the service providers in income
    private float DeliveryManRate=0.4F;
    private float ServiceProvidersRate=0.6F;

    public ServiceProvider(){

    }
    public ServiceProvider(float payableBallance, float availableBalance, float amount){
        this.payableBallance = payableBallance;
        this.AvailableBalance =availableBalance;

    }

    public float getPayableBallance() {
        return payableBallance;
    }

    public void setPayableBallance(float payableBallance) {
        this.payableBallance = payableBallance;
    }

    public float getAvailableBalance() {
        return AvailableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        AvailableBalance = availableBalance;
    }



    public  float setTotalServiceCharge(float amount) {
        float totalServiceCharge=0;
        float initial=50;
        if(amount<=500){
            totalServiceCharge=50 + amount*0.1F;
        }
        else if(amount>500 && amount <=5000){
            totalServiceCharge =50+ amount*0.05F;
        }
        else if(amount >5000 && amount<=10000){
            totalServiceCharge=50 +amount*0.03F;
        }
        else{
            totalServiceCharge=50+amount*0.02F;
        }
        return totalServiceCharge;
    }

    public void paymentForSuperMarket(String ProductId,float priceForThatParticularProduct){
        try{
            PreparedStatement stm=con.prepareStatement("Update SuperMarkets set AccountBalance=AccountBalance+? where SUserName=(Select SUserName from Product where ProductId=?)");
            stm.setFloat(1,priceForThatParticularProduct);
            stm.setString(2,ProductId);
            stm.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }



    public void DeductProductAmount(int number,String ProductId){
        try{
            PreparedStatement stm=con.prepareStatement("Update Product set NumberProductAvailable=NumberProductAvailable-? where ProductId=?");
            stm.setInt(1,number);
            stm.setString(2,ProductId);
            stm.executeUpdate();


            PreparedStatement stm2=con.prepareStatement("Update Product set ProductSold=ProductSold+? where ProductId=?");
            stm2.setInt(1,number);
            stm2.setString(2,ProductId);
            stm2.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }


    public void setServiceProvidersBallance(Float totalServiceCharge){

        try{
            PreparedStatement stm=con.prepareStatement("Update  ServiceProvider set payableBallance	=payableBallance+?");
            stm.setFloat(1,totalServiceCharge*this.DeliveryManRate);
            stm.executeUpdate();


            PreparedStatement stm2=con.prepareStatement("Update  ServiceProvider set AvailableBalance=?");
            stm2.setFloat(1,totalServiceCharge*this.ServiceProvidersRate);

            stm2.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }


    float PriceOfProduct(String ProductId){
            try{
                PreparedStatement stm=DatabaseConnection.con.prepareStatement("Select * from product  where ProductId=?");
                stm.setString(1,ProductId);
                ResultSet result=stm.executeQuery();
                result.next();
                return  result.getFloat(7);

            }
            catch (SQLException e){
                System.out.println(e);
            }


        return  0;
    }


    public void  deliveryManBallance(){

        float  TotalPriceOfDeliverdGoods;
        ArrayList<Orders> orders=new ArrayList<>();
        orders=Orders.getOrderDB();
        try{
            for(int i=0;i<orders.size();i++){

                TotalPriceOfDeliverdGoods=orders.get(i).getAmountofProduct()*this.PriceOfProduct(orders.get(i).getProductId());
                if(orders.get(i).getOrderstatus().equals("Accepted")){
                    for(int j=i+1;j<orders.size();j++){
                        if(orders.get(i).getCUserName().equals(orders.get(j).getCUserName()) && orders.get(i).getTimeOfPurchase().equals(orders.get(j).getTimeOfPurchase()) ){
                            TotalPriceOfDeliverdGoods=TotalPriceOfDeliverdGoods+(orders.get(j).getAmountofProduct()*this.PriceOfProduct(orders.get(j).getProductId()));
                            orders.remove(j);
                        }
                    }
                    PreparedStatement stm=con.prepareStatement("update DeliveryMans set AccountBalance=AccountBalance+? where DMUserName=(Select DMUserName from OrderPickedBy where TimeOfPurchase=? and CUserName=?)");
                    stm.setFloat(1,setTotalServiceCharge(TotalPriceOfDeliverdGoods)*this.DeliveryManRate);
                    stm.setString(2,orders.get(i).getTimeOfPurchase());
                    stm.setString(3,orders.get(i).getCUserName());
                    stm.executeUpdate();



                    PreparedStatement stm2=con.prepareStatement("update  Orders set OrderStatus=?  where TimeOfPurchase=? and CUserName=?");
                    stm2.setString(1,"Complited");
                    stm2.setString(2,orders.get(i).getTimeOfPurchase());
                    stm2.setString(3,orders.get(i).getCUserName());
                    stm2.executeUpdate();



                    PreparedStatement stm3=con.prepareStatement("Update  ServiceProvider set payableBallance=payableBallance-?");
                    stm3.setFloat(1,TotalPriceOfDeliverdGoods*this.DeliveryManRate);
                    stm3.executeUpdate();
                }







            }
            }


        catch (SQLException e){
            System.out.println(e);
        }

    }

    public void DeductCustomerBallance(String CUserName,Float TotalCost){
        try{
            PreparedStatement stm=con.prepareStatement("Update Customers set AccountBalance=AccountBalance-? where CUserName=?");
            stm.setFloat(1,TotalCost);
            stm.setString(2,CUserName);
            stm.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }








}
