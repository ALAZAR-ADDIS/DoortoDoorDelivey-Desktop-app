import javax.swing.*;
import java.util.ArrayList;

public class Grocery extends LoginInformation {
    //list of product for a particular Grocery
    private  ArrayList<product> products=new ArrayList<>();
    private float rate;

    //grocery class constructor

    public Grocery(String fullname,String id,String email,String phonenumber,String location,float Rate){
        super(fullname,id,email,phonenumber,location);
        rate=Rate;
    }

    /**
     * delete get add and update data from he database
     *
     */

    public void setProducts(ArrayList<product> groceryProducts){

        /*
        sql query to add the product  to the database
         */
    }
    public static void getProducts(){
        /*
        Sql query to get the product by id
         */
    }





    /**
     * settes  method for the company
     * @param rate
     */
    void setRate(float rate){
       /*
        sql query using id
         */
    }


    /**
     * add and delete Grocery man from the database
     */

    public void  addGrocerytoDB(){
        /*
        SQL query to  the data
         */
    }

    public static void getGrocerytoDB(){
        /*
        Sql delete query
         */
    }

}


