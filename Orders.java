import java.util.ArrayList;

public class Orders{
    private  String customerName;
    private  String customerId;
    private  String customerPhoneNumber;
    private String customerLocation;

    private String groceryName;
    private String groceryrId;
    private String grocetyPhoneNumber;
    private String goroceryLocation;
    private int numberofProductstoOrder;
    private String orderid;

    private  boolean orderstatus=false;//if  it is delivered or not

    public Orders(String customerName, String customerId, String customerPhoneNumber, String customerLocation, String groceryName, String groceryrId, String grocetyPhoneNumber, String goroceryLocation, int numberofProductstoOrder) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerLocation = customerLocation;
        this.groceryName = groceryName;
        this.groceryrId = groceryrId;
        this.grocetyPhoneNumber = grocetyPhoneNumber;
        this.goroceryLocation = goroceryLocation;
        this.numberofProductstoOrder = numberofProductstoOrder;
        this.orderid = "";
    }



    //getter and setter
    public void setcustomerName(String customerName){
        /*
        query using id
         */
    }
    public String getcustomerName(){
        /*
        query using id
         */
        return "";
    }

    public void setcustomerId(String customerId){
        /*
        query using id
         */

    }
    public String getcustomerId(){
         /*
        query using id
         */
        return "";
    }

    public void setCustemerphonenumber(String custemerphonenumber){
         /*
        query using id
         */
    }
    public String getCustemerphonenumber(){
         /*
        query using id
         */
        return "";
    }

    public void setcustomerLocation(String customerLocation){
         /*
        query using id
         */
    }
    public String getcustomerLocation(){
         /*
        query using id
         */
        return "";
    }




    public void setgroceryNamename(String groceryNamename){
  /*
        query using id
         */

    }
    public String getgroceryNamename(){
 /*
        query using id
         */
        return "";
    }

    public void setgroceryrIdid(String groceryrId){

         /*
        query using id
         */
    }
    public String getgroceryNameid(){

         /*
        query using id
         */

        return "";
    }

    public void setgrocetyPhoneNumber(String grocetyPhoneNumber){

         /*
        query using id
         */
    }
    public String getgrocetyPhoneNumber(){

       /*
        query using id
         */

        return "";
    }

    public void setgoroceryLocation(String goroceryLocation){

         /*
        query using id
         */
    }
    public String getgoroceryLocation(){
             /*
        query using id
         */
        return "";
    }


    public void setOrderid(String orderid){

         /*
        query using id
         */
    }
    public String getOrderid(){

         /*
        query using id
         */

        return "";
    }


    public void setOrderstatuset(boolean orderstatus){

         /*
        query using id
         */
    }
    public boolean getOrderstatuset(){
        /*
        query using id
         */
        return false;
    }

    public void  addOrdertoDB(){
        /*
        SQL query to  the data
         */
    }

    public static void getOrderDB(){
        /*
        Sql delete query
         */
    }


}