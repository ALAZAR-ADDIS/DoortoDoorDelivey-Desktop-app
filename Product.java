import java.util.ArrayList;

class product{
    private String productName;
    private String productDescription;
    private String productId;
    private int numberAvailable;
    private float price;


    //product constructor
    public product(String productName,String productDescription,String productId,int numberofproduct,float price){
        this.productName=productName;
        this.productDescription=productDescription;
        this.productId=productId;
        this.numberAvailable=numberofproduct;
        this.price=price;
    }

    //getter and setters
    public void setProductName(String productName){
        /*
          sql query
         */
    }
    public String getProductName(){

        /*
          sql query
         */
        return "";
    }


    public void setProductDescription(String productDescription){
        /*
          sql query
         */
    }
    public String getProductDescription(){
        /*
          sql query
         */
        return "";
    }


    public void setProductId(String productId){
        /*
          sql query
         */
    }
    public String getProductId(){
        /*
          sql query
         */
        return "";
    }



    public void setNumberofProduct(int numberofproduct){
        
        /*
          sql query
         */
    }
    public int getNumberofproduct(){

        /*
        sql query
         */
        
        return 1;
    }



    public void setPrice(float price){
        /*
        sql query
         */
    }
    public float getprice(){
        /*
        Sql query
         */
        
        return 1 ;
    }


    /**
     * update delete product from the database
     */

    public static void updateProduct(){
        /*
        SQl query that updates  based onthe id
         */

    }

    public static void deleteProduct(){
        /*
        SQl query that delete  based on the id
         */

    }

    public static void addProduct(){
        /*
        SQl query that add  product
         */

    }

    public static void getProduct(){
        /*
        SQl query that add  product
         */

    }
    }