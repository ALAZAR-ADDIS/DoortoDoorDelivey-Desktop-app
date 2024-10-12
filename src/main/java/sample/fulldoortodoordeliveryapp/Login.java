package sample.fulldoortodoordeliveryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.fulldoortodoordeliveryapp.SourceCode.Customer;
import sample.fulldoortodoordeliveryapp.SourceCode.Deliveryman;
import sample.fulldoortodoordeliveryapp.SourceCode.LoginInformation;
import sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    byte[] decomposedImageProfile;
    byte [] decomposedImageId;
    Alert alert;

    private byte[] imageTobyte()  {//decompose image in to binary
        FileChooser file = new FileChooser();
        File SelectedFile = file.showSaveDialog(null);


        String dir = String.valueOf(SelectedFile);
        String extention = dir.substring(dir.lastIndexOf(".") + 1);

        try{
            BufferedImage image = ImageIO.read(SelectedFile);
            ByteArrayOutputStream By = new ByteArrayOutputStream();
            ImageIO.write(image, extention, By);
            return By.toByteArray();}
        catch(IOException e) {
            System.out.println(e);
        }
        return null;

    }

    LoginInformation determineRole(String UserName){
        LoginInformation loginInfo=new LoginInformation();
        loginInfo=Customer.getCustomerfromDB(UserName);
        if(loginInfo.getRole()==null){
            loginInfo=Deliveryman.getDeliverymanfromDB(UserName);
            if(loginInfo.getRole()==null){
                loginInfo=SuperMarket.getSuperMarkettoDB(UserName);
            }
        }

        return loginInfo;


    }



    @FXML
    private BorderPane SiginBp;


    /**
     * Customer sign in part
     */

    @FXML
    private ImageView CustomerPic;

    @FXML
    private TextField CustomerEmail;

    @FXML
    private RadioButton CustomerFemale;

    @FXML
    private TextField CustomerFirstName;

    @FXML
    private TextField CustomerLastName;

    @FXML
    private TextField CustomerLocation;

    @FXML
    private RadioButton CustomerMale;

    @FXML
    private PasswordField CustomerPassword;

    @FXML
    private TextField CustomerPhoneNumber;

    @FXML
    private TextField CustomerUserName;

    @FXML
    private TextField CustomerWallet;

    @FXML
    private PasswordField CutomerConfirmPassword;

    @FXML
    private ToggleGroup CutomerGender;

    @FXML
    private ScrollPane CutomerScrollPane;



    @FXML
    void CustomerProfilePicture(MouseEvent event) {
        decomposedImageProfile=this.imageTobyte();
        ByteArrayInputStream byt = new ByteArrayInputStream(decomposedImageProfile);
        Image fx = new Image(byt);
        CustomerPic.setImage(fx);


    }

    @FXML
    void CutomerSignUp(MouseEvent event) {//sign up button clicked

        String Gender=null;
        try{
            if(CutomerGender.getSelectedToggle().equals("Male")){
                Gender="Male";

            }
            else{
                Gender="Female";
            }
        }
        catch (NullPointerException e){
            System.out.println(e);

        }




        if(CustomerFirstName.getText().equals("")||CustomerLastName.getText().equals("")||CustomerUserName.getText().equals("")||CustomerPassword.getText().equals("")||CustomerEmail.getText().equals("")||CustomerPhoneNumber.getText().equals("")||CustomerLocation.getText().equals("")|| CustomerWallet.getText().equals("") ||decomposedImageProfile==null||Gender.equals("")){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all the required information's");
            alert.showAndWait();
        }
        else{
            if(CustomerPassword.getText().equals(CutomerConfirmPassword.getText())){
                try{
                    if(determineRole(CustomerUserName.getText()).getRole()==null){
                Customer SigninCustomerInstance=new Customer(CustomerFirstName.getText(),CustomerLastName.getText(),CustomerUserName.getText(),"Customer",CustomerPassword.getText(),CustomerEmail.getText(),CustomerPhoneNumber.getText(),CustomerLocation.getText(),Float.parseFloat(CustomerWallet.getText()),decomposedImageProfile,Gender);
                SigninCustomerInstance.addCustomertoDB();
                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Signed up Successfully");
                    alert.showAndWait();}
                else{
                        alert=new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("This userName is taken by other Users");
                        alert.showAndWait();
                    }}
                catch (NumberFormatException e){
                    System.out.println(e);
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("The wallet that you set must contatin only a  number");
                    alert.showAndWait();
                }
            }
            else{
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("your password and confirmation password mismatched");
                alert.showAndWait();
            }
         }

    }










    @FXML
    private PasswordField DeliveryManConfirmPassword;

    @FXML
    private TextField DeliveryManEmail;

    @FXML
    private RadioButton DeliveryManFemale;

    @FXML
    private TextField DeliveryManFirstName;

    @FXML
    private ToggleGroup DeliveryManGender;

    @FXML
    private ImageView DeliveryManIdpic;

    @FXML
    private TextField DeliveryManLastName;

    @FXML
    private TextField DeliveryManLocation;

    @FXML
    private RadioButton DeliveryManMale;

    @FXML
    private PasswordField DeliveryManPassword;

    @FXML
    private TextField DeliveryManPhoneNumber;

    @FXML
    private ImageView DeliveryManPic;

    @FXML
    private ScrollPane DeliveryManScrollPane;

    @FXML
    private TextField DeliveryManUserName;
    @FXML
    private ComboBox<String> DeliveyManVehicleType;

    ObservableList vehicleType=FXCollections.observableArrayList("Car","Moterbicycle","bicycle");


    @FXML
    void DeliveryManIDPic(MouseEvent event) {
        decomposedImageId=this.imageTobyte();
        ByteArrayInputStream byt = new ByteArrayInputStream(decomposedImageId);
        Image fx = new Image(byt);
        DeliveryManIdpic.setImage(fx);

    }

    @FXML
    void DeliveryManProfilePicture(MouseEvent event) {
        decomposedImageProfile=this.imageTobyte();
        ByteArrayInputStream byt = new ByteArrayInputStream(decomposedImageProfile);
        Image fx = new Image(byt);
        DeliveryManPic.setImage(fx);
    }

    @FXML
    void DeliveryManSignUp(MouseEvent event) {
        String Gender=null;
        try{
            if(DeliveryManGender.getSelectedToggle().equals("Male")){
                Gender="Male";

            }
            else{
                Gender="Female";
            }}
        catch (NullPointerException e){
            System.out.println(e);

        }




        if(DeliveryManFirstName.getText().equals("")||DeliveryManLastName.getText().equals("")||DeliveryManUserName.getText().equals("")||DeliveryManPassword.getText().equals("")||DeliveryManEmail.getText().equals("")||DeliveryManPhoneNumber.getText().equals("")||DeliveryManLocation.getText().equals("")|| decomposedImageProfile==null||decomposedImageId==null||Gender.equals("")||DeliveyManVehicleType.getItems()==null){

            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all the required information's");
            alert.showAndWait();
        }
        else{
            if(DeliveryManPassword.getText().equals(DeliveryManConfirmPassword.getText())){


                if(determineRole(DeliveryManUserName.getText()).getRole()==null){
                    Deliveryman SigninDeliveryInstance=new Deliveryman(DeliveryManFirstName.getText(),DeliveryManLastName.getText(),DeliveryManUserName.getText(),"DeliveryMan" ,DeliveryManPassword.getText(),DeliveryManEmail.getText(),DeliveryManPhoneNumber.getText(),DeliveryManLocation.getText(),0,decomposedImageProfile,Gender,DeliveyManVehicleType.getValue(),decomposedImageId);
                    SigninDeliveryInstance.addDeliveryMantoDB();
                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Signed up Successfully");
                    alert.showAndWait();}
                else{
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This userName is taken by other Users");
                    alert.showAndWait();
                }


            }
            else{
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("your password and confirmation password mismatched");
                alert.showAndWait();
            }
        }


    }





    @FXML
    private TextField SuperMarkerEmail;

    @FXML
    private PasswordField SuperMarketConfirmPassword;

    @FXML
    private TextField SuperMarketFirstName;

    @FXML
    private TextField SuperMarketLocation;

    @FXML
    private PasswordField SuperMarketPassword;

    @FXML
    private TextField SuperMarketPhoneNumber;

    @FXML
    private ImageView SuperMarketPic1;

    @FXML
    private ScrollPane SuperMarketScrollPane;

    @FXML
    private TextField SuperMarketUserName;


    @FXML
    void SuperMarketProfilePicture(MouseEvent event) {
        decomposedImageProfile=this.imageTobyte();
        ByteArrayInputStream byt = new ByteArrayInputStream(decomposedImageProfile);
        Image fx = new Image(byt);
        SuperMarketPic1.setImage(fx);

    }

    @FXML
    void SuperMarketSignUp(MouseEvent event) {
        if(SuperMarketFirstName.getText().equals("")||SuperMarketUserName.getText().equals("")||SuperMarketPassword.getText().equals("")||SuperMarkerEmail.getText().equals("")||SuperMarketPhoneNumber.getText().equals("")||SuperMarketLocation.getText().equals("")|| decomposedImageProfile==null){

            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all the required information's");
            alert.showAndWait();
        }
        else{
            if(SuperMarketPassword.getText().equals(SuperMarketConfirmPassword.getText())){

                if(determineRole(SuperMarketUserName.getText()).getRole()==null){
                    SuperMarket SigninSuperMarketInstance=new SuperMarket(SuperMarketFirstName.getText(),"",SuperMarketUserName.getText(),"SuperMarket" ,SuperMarketPassword.getText(),SuperMarkerEmail.getText(),SuperMarketPhoneNumber.getText(),SuperMarketLocation.getText(),0,decomposedImageProfile);

                    SigninSuperMarketInstance.addSuperMarkertoDB();
                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Signed up Successfully");
                    alert.showAndWait();}
                else{
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This userName is taken by other Users");
                    alert.showAndWait();
                }



            }
            else{
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("your password and confirmation password mismatched");
                alert.showAndWait();
            }
        }



    }















    /**
     * login
     */
    @FXML
    private AnchorPane LoginAnchor;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUserName;



    /**
     *
     * Login in action Listner
     */

    @FXML
    void CreateNewAcount(MouseEvent event) {
         LoginAnchor.setVisible(false);
         SiginBp.setVisible(true);
    }

    @FXML
    void LoginButton(MouseEvent event) throws IOException {//Login button clicked
       LoginInformation LoginInf=determineRole(LoginUserName.getText());

        if(LoginInf.getRole()!=null){
            if(LoginInf.getPassword().equals(LoginPassword.getText())){
                StoreData.Info=LoginInf;
                if(LoginInf.getRole().equals("Customer")){
                    Stage CurrentStage=(Stage)LoginPassword.getScene().getWindow();
                    CurrentStage.close();
                    FXMLLoader  loader= new FXMLLoader(getClass().getResource("Customer.fxml"));
                    Scene scene=new Scene(loader.load());
                    Stage stage=new Stage();
                    stage.setTitle("Customer");
                    stage.setScene(scene);
                    stage.show();
                }
                else  if(LoginInf.getRole().equals("DeliveryMan")){
                    Stage CurrentStage=(Stage)LoginPassword.getScene().getWindow();
                    CurrentStage.close();
                    FXMLLoader  loader= new FXMLLoader(getClass().getResource("DeliveryMan.fxml"));
                    Scene scene=new Scene(loader.load());
                    Stage stage=new Stage();
                    stage.setTitle("DeliveryMan");
                    stage.setScene(scene);
                    stage.show();

                }
                else{
                    Stage CurrentStage=(Stage)LoginPassword.getScene().getWindow();
                    CurrentStage.close();
                    FXMLLoader  loader= new FXMLLoader(getClass().getResource("SuperMarket.fxml"));
                    Scene scene=new Scene(loader.load());
                    Stage stage=new Stage();
                    stage.setTitle("SuperMarket");
                    stage.setScene(scene);
                    stage.show();

                }

            }else{
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login info");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Password");
                alert.showAndWait();
            }
        }
        else{
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login info");
            alert.setHeaderText(null);
            alert.setContentText("Such userName doesn't exist ");
            alert.showAndWait();
        }

    }


    /**
     *
     * Sign in action listner area  on the   vbox
     */

    @FXML
    void BackToLogin(MouseEvent event) {
          SiginBp.setVisible(false);
          LoginAnchor.setVisible(true);
    }

    @FXML
    void SigninCutomer(MouseEvent event) {
        CutomerScrollPane.setVisible(true);
        DeliveryManScrollPane.setVisible(false);
        SuperMarketScrollPane.setVisible(false);

    }

    @FXML
    void SigninDeliveryMan(MouseEvent event) {
        CutomerScrollPane.setVisible(false);
        DeliveryManScrollPane.setVisible(true);
        SuperMarketScrollPane.setVisible(false);
    }

    @FXML
    void SigninSuperMarket(MouseEvent event) {
        CutomerScrollPane.setVisible(false);
        DeliveryManScrollPane.setVisible(false);
        SuperMarketScrollPane.setVisible(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DeliveyManVehicleType.setItems(vehicleType);//combo box for delivery man

    }
}
