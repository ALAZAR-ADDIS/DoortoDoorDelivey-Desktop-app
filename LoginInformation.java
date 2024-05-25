public class LoginInformation implements Getter_Setter{
    //instance variables of Logininformation class
    private final  String fullName;
    private  final String id;

    private String email;
    private String phoneNumber;
    private String location;
    private float  AvailableBalance;


    //LoginInformation class constructor

    public LoginInformation(String fullName ,String id,String email,String phoneNumber,String location){
        AvailableBalance=0;
        this.fullName=fullName;
        this.id=id;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.location=location;
    }


    //implements of Getter_Setter(interface) for the Logininformation class
    @Override
    public String getFullname() {
        /*
        query
         */
        return "";
    }

    @Override
    public String getId() {
       /*
        query
         */
        return "";
    }

    @Override
    public String getEmail() {
       /*
        query
         */
        return "";
    }

    @Override
    public void UpdateEmail(String email) {
        /*
        query
         */


    }

    @Override
    public String getPhoneNumber() {
        /*
        query
         */
        return "";
    }

    @Override
    public void UpdatePhoneNumber(String phoneNumber) {
       /*
        query
         */

    }

    @Override
    public String getLocation() {
       /*
        query
         */
        return "";
    }

    @Override
    public void updateLocation(String location) {
        /*
        query
         */

    }

    @Override
    public   float getAvailableBallace(){
        /*
        sql query
         */

        return 1;
    }
    @Override
    public void setAvailableBallance(float AvailableBallance){
        /*
        sql query
         */

    }



}
