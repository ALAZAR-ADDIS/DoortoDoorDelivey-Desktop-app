class ServiceProvider{
    private  float payableBallance;
    private float AvailableBalance;
    public static float rate =0.2F;


    /**
     *  setter and geter method of payableBallance
     * @payableBallance
     */
    public float getpayableBallance() {

        /*
          sql query
         */
        return 1;
    }

    public void setpayableBallance(float priceofProduct){

        /*
        sql query
         */

        //this.payableBallance=this.payableBallance-(priceofProduct+ priceofProduct*ServiceProvider.rate);
    }


    /**
     * setter and getter method of AvailableBallance
     * @AvailableBallance
     */

    public float getAvailableBalance() {

        /*
          sql query
         */
        return 1;
        //return  AvailableBalance;
    }

    public void setAvailableBalance(float serviceCharge){
        /*
          sql query
         */
        //this.AvailableBalance=this.AvailableBalance +serviceCharge;

    }

    /**
     *
     * calculates and calls setAvailableBalance and setpayableBallance
     */

    public void servicecalculator(float priceofProduct){
        /*
        sql query
         */
    }


}