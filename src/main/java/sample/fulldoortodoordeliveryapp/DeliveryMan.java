package sample.fulldoortodoordeliveryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.fulldoortodoordeliveryapp.SourceCode.AcceptedOrder;
import sample.fulldoortodoordeliveryapp.SourceCode.Orders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeliveryMan implements Initializable {
    private String CustomerUserName;//for the AO image inserting section
    private String timePurchase;
    byte [] decomposedimage;

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
    @FXML
    private Label AOImageLabel;

    @FXML
    private ImageView AOInvoiceImage;
    @FXML
    private TableColumn<AcceptedOrder,String> AorderCUserName;

    @FXML
    private TableColumn<AcceptedOrder, String> AOrderProducId;

    @FXML
    private TableColumn<AcceptedOrder,String> ASuperMarketName;

    @FXML
    private TableColumn<AcceptedOrder,Integer> AorderAmount;

    @FXML
    private TableColumn<AcceptedOrder,Byte> AorderInvoiceImage;

    @FXML
    private TableColumn<AcceptedOrder, String> AorderCustomerName;

    @FXML
    private TableColumn<AcceptedOrder,String> AorderCustomerPno;

    @FXML
    private TableView<AcceptedOrder> AorderInfoTable;

    @FXML
    private TableColumn<AcceptedOrder,String> AorderSuperMarkerPno;

    @FXML
    private TableColumn<AcceptedOrder,String> AorderSuperMarketLocation;

    @FXML
    private TableColumn<AcceptedOrder,String> AorderTimeofPurchase;
    @FXML
    private AnchorPane DeliveryManBallance;

    @FXML
    private Label DeliveryManName;

    @FXML
    private AnchorPane DeliveryManOrders;

    @FXML
    private ImageView DeliveryManProfilePic;

    @FXML
    private Label DeliveryManUserName;
    @FXML
    private TableColumn<Orders,Integer> OrderAmount;

    @FXML
    private TableColumn<Orders,String> OderTimeOfPurchase;

    @FXML
    private TableColumn<Orders, String> OrderPickUpLocation;

    @FXML
    private TableColumn<Orders, String> OrderProductId;

    @FXML
    private TableView<Orders> OrderTable;

    @FXML
    private TableColumn<Orders, String> OrderUsername;

    @FXML
    private TextField OrderTextCuxtomerUserName;

    @FXML
    private TextField OrderTextPID;

    @FXML
    private TextField OrderTextPUL;

    @FXML
    private TextField OrderTextTimeOfPurchase;




    @FXML
    private AnchorPane DeliveryManOrdersPage;

    @FXML
    void BackToLogin(MouseEvent event) throws IOException {
        Stage CurrentStage=(Stage)DeliveryManBallance.getScene().getWindow();
        CurrentStage.close();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene=new Scene(loader.load());
        Stage stage=new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void DeliveryManBallance(MouseEvent event) {
        DeliveryManBallance.setVisible(true);
        DeliveryManOrdersPage.setVisible(false);

    }




    @FXML
    void DeliveryManOrders(MouseEvent event) {
        DeliveryManBallance.setVisible(false);
        DeliveryManOrdersPage.setVisible(true);

    }



    @FXML
    void OrderTableClicked(MouseEvent event) {

        Orders ordertbl=OrderTable.getSelectionModel().getSelectedItem();
        OrderTextPID.setText(ordertbl.getProductId());
        OrderTextPUL.setText(ordertbl.getPickUpLocation());
        OrderTextTimeOfPurchase.setText(ordertbl.getTimeOfPurchase());
        OrderTextCuxtomerUserName.setText(ordertbl.getCUserName());

    }

    @FXML
    void OrderAccepteBtn(MouseEvent event) {
      Orders.SetOrderStatus("OnTheWay",OrderTextCuxtomerUserName.getText(),OrderTextTimeOfPurchase.getText());
      Orders.SetOrderPickedBy(OrderTextCuxtomerUserName.getText(),OrderTextTimeOfPurchase.getText(),StoreData.Info.getUserName(), OrderTextPID.getText());
      refreshOrderTable();
      AOtablerefrasher();

    }
    ObservableList<Orders> ordersList=FXCollections.observableArrayList();

    void refreshOrderTable(){
        ordersList.clear();
        for(Orders ord:Orders.getOrderDB()) {
            if(ord.getOrderstatus().equals("OnProcess"))
                ordersList.add(ord);
        }

    }

    void initializeOrderTable(){
        refreshOrderTable();


        OderTimeOfPurchase.setCellValueFactory(new PropertyValueFactory<>("TimeOfPurchase"));
        OrderPickUpLocation.setCellValueFactory(new PropertyValueFactory<>("pickUpLocation"));
        OrderProductId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        OrderUsername.setCellValueFactory(new PropertyValueFactory<>("CUserName"));
        OrderAmount.setCellValueFactory(new PropertyValueFactory<>("AmountofProduct"));
        OrderTable.setItems(ordersList);

    }


    ObservableList<AcceptedOrder> AcceptedOrdersList=FXCollections.observableArrayList();
     void AOtablerefrasher(){

        AcceptedOrdersList.clear();
        AcceptedOrdersList.addAll(AcceptedOrder.getAcceptedDB(StoreData.Info.getUserName()));

    }

     void AOInitializer(){
        AOtablerefrasher();
        AOrderProducId.setCellValueFactory(new PropertyValueFactory<>("AOrderProducId"));
       ASuperMarketName.setCellValueFactory(new PropertyValueFactory<>("ASuperMarketName"));
       AorderInvoiceImage.setCellValueFactory(new PropertyValueFactory<>("AorderImage"));
       AorderCustomerName.setCellValueFactory(new PropertyValueFactory<>("AorderCustomerName"));
       AorderCustomerPno.setCellValueFactory(new PropertyValueFactory<>("AorderCustomerPno"));
       AorderSuperMarkerPno.setCellValueFactory(new PropertyValueFactory<>("AorderSuperMarkerPno"));
       AorderSuperMarketLocation.setCellValueFactory(new PropertyValueFactory<>("AorderSuperMarketLocation"));
       AorderTimeofPurchase.setCellValueFactory(new PropertyValueFactory<>("AorderTimeofPurchase"));
       AorderAmount.setCellValueFactory(new PropertyValueFactory<>("AorderAmount"));
       AorderCUserName.setCellValueFactory(new PropertyValueFactory<>("AorderCUserName"));
       AorderInfoTable.setItems(AcceptedOrdersList);



    }

    void InitalizeName(){
        DeliveryManUserName.setText(StoreData.Info.getUserName());
        String FirstName=StoreData.Info.getFirstName();
        FirstName=StoreData.Info.getFirstName().substring(0,1).toUpperCase()+StoreData.Info.getFirstName().substring(1).toLowerCase();
        String LastName=StoreData.Info.getLastName();
        LastName=StoreData.Info.getLastName().substring(0,1).toUpperCase()+StoreData.Info.getLastName().substring(1).toLowerCase();
        DeliveryManName.setText(FirstName+ " "+LastName);
    }



    @FXML
    void AOTableClicked(MouseEvent event) {
         AOInvoiceImage.setImage(null);
         AcceptedOrder ao=AorderInfoTable.getSelectionModel().getSelectedItem();
         AOImageLabel.setText(ao.getAorderCustomerName());
         if(ao.getAorderImage()!=null){
             ByteArrayInputStream byt = new ByteArrayInputStream(ao.getAorderImage());
             Image fx = new Image(byt);
             AOInvoiceImage.setImage(fx);
         }
        this.CustomerUserName=ao.getAorderCUserName();
         this.timePurchase=ao.getAorderTimeofPurchase();



    }

    @FXML
    void AOImageClicked(MouseEvent event) {
        decomposedimage=imageTobyte();
        ByteArrayInputStream byt = new ByteArrayInputStream(decomposedimage);
        Image fx = new Image(byt);
        AOInvoiceImage.setImage(fx);

    }

    @FXML
    void AOSummiteImageBtn(MouseEvent event) {
        Orders.addInvoiceTODB(decomposedimage,this.CustomerUserName,this.timePurchase);
        AOInvoiceImage.setImage(null);
         AOtablerefrasher();
    }


    /*
    ballance page
     */
    @FXML
    private Label DeliveryManBallanceLabel;

    void DeliveryManBallance(){
        DeliveryManBallanceLabel.setText(String.valueOf(StoreData.Info.getAvailableBallace()));
    }

    void deliveryManProfileInitializer(){
        ByteArrayInputStream byt = new ByteArrayInputStream(StoreData.Info.getimage());
        Image fx = new Image(byt);
        DeliveryManProfilePic.setImage(fx);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeOrderTable();
        InitalizeName();
        AOInitializer();
        DeliveryManBallance();
        deliveryManProfileInitializer();


    }
}
