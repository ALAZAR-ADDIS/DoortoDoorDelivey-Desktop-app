package sample.fulldoortodoordeliveryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.fulldoortodoordeliveryapp.SourceCode.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Customer implements Initializable {
    Alert alert;
    private ServiceProvider serviceProvider=new ServiceProvider();
    private float Total=0;//for calculating the full cost
    private Boolean WantToSearch =false;

    @FXML
    private TextField SearchTextFild;


    @FXML
    void SearchClearBtn(MouseEvent event) {
        System.out.println("Hello");
        WantToSearch=false;
        SetItemstoGrid();
        System.out.println(SearchTextFild.getText());
        SearchTextFild.clear();

    }

    @FXML
    void Searchbtn(MouseEvent event) {
        System.out.println("Hello2");
        WantToSearch=true;
        SetItemstoGrid();

    }







    @FXML
    private GridPane ItemDisplayGrid;
    @FXML
    private ScrollPane ItemDisplayScrollPane;

    @FXML
    private AnchorPane BallanceAnchor;
    @FXML
    private AnchorPane PurchaseAnchor;

    @FXML
    private ImageView CustomerProfilePic;


    @FXML
    private Label CustomerUserName;

    @FXML
    private AnchorPane OrderStatusAnchor;


    @FXML
    private ScrollPane PurchaseScrollPan;

    @FXML
    private Label customerFullName;


    @FXML
    void BackToLogin(MouseEvent event) throws IOException {
        Stage CurrentStage=(Stage)customerFullName.getScene().getWindow();
        CurrentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void CustomerBallancePage(MouseEvent event) {
           BallanceAnchor.setVisible(true);
           OrderStatusAnchor.setVisible(false);
        PurchaseAnchor.setVisible(false);
    }

    @FXML
    void CustomerOrderStatuPage(MouseEvent event) {
        BallanceAnchor.setVisible(false);
        OrderStatusAnchor.setVisible(true);
        PurchaseAnchor.setVisible(false);

    }


    @FXML
    void CustomerPurchasePage(MouseEvent event) {
        BallanceAnchor.setVisible(false);
        OrderStatusAnchor.setVisible(false);
        PurchaseAnchor.setVisible(true);
    }
    void composeImage(){
        System.out.println("Hello"+StoreData.Info.getimage());
        ByteArrayInputStream byt = new ByteArrayInputStream(StoreData.Info.getimage());
        Image fx = new Image(byt);
        CustomerProfilePic.setImage(fx);
    }

    void initializeName(){
        CustomerUserName.setText(StoreData.Info.getUserName());
        String FirstName=StoreData.Info.getFirstName();
        FirstName=StoreData.Info.getFirstName().substring(0,1).toUpperCase()+StoreData.Info.getFirstName().substring(1).toLowerCase();
        String LastName=StoreData.Info.getLastName();
        LastName=StoreData.Info.getLastName().substring(0,1).toUpperCase()+StoreData.Info.getLastName().substring(1).toLowerCase();
        customerFullName.setText(FirstName+ " "+LastName);
    }

    ObservableList<product> AllProduct= FXCollections.observableArrayList();
    public ArrayList<product> allProduct(){
        return product.getProductDb();
    }

    void SetItemstoGrid()  {
        ItemDisplayGrid.getChildren().clear();//to cleare the whole grid
        int col=0;
        int row=1;
        ItemDisplayGrid.getColumnConstraints().clear();
        ItemDisplayGrid.getRowConstraints().clear();
        AllProduct.clear();
        if(WantToSearch){
            AllProduct.addAll(product.SerchProduct(SearchTextFild.getText()));

       }
        else{ AllProduct.addAll(allProduct());}

        if(AllProduct.size()==0){
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No Product found");
            alert.showAndWait();
        }



        for(product p:AllProduct){

            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("item.fxml"));
                AnchorPane anchor=loader.load();

                Item prod=loader.getController();
                prod.setCustomerController(this);
                prod.InitializeProduct(p);


                if(col==4){
                    col=0;
                    row++;
                }

                ItemDisplayGrid.add(anchor,col++,row);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }
    }





    /*
    cart
     */


    @FXML
    private Label CartChange;

    @FXML
    private TextField CartPickUpLocation;

    @FXML
    private TableColumn<Cart, Integer> CartAmount;

    @FXML
    private TableColumn<Cart, String> CartName;

    @FXML
    private TextField CartPayTextFild;

    @FXML
    private TableColumn<Cart, Float> CartPrice;

    @FXML
    private TableColumn<Cart, String> CartProductId;

    @FXML
    private Button CartRemoveProduct;

    @FXML
    private TableView<Cart> CartTable;

    @FXML
    private Label CartTotalPrice=new Label();

    ObservableList<Cart> CartList=FXCollections.observableArrayList();



    @FXML
    void RemovefromCart(MouseEvent event) {
    try{
     Cart cart= CartTable.getSelectionModel().getSelectedItem();
     Cart.DeleteCartDb(cart.getProductId(),cart.getCUsername());}
    catch (NullPointerException e){
        alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Your cart is empty");
        alert.showAndWait();

    }
     cartRefrasher();
    }

    public  void cartRefrasher(){

        CartList.clear();
        CartList.addAll(Cart.getCartDb(StoreData.Info.getUserName()));
        //to remove redendancy of items to a cart
        for(int i=0;i<CartList.size();i++){
            for(int j=i+1;j<CartList.size();j++){
                if(CartList.get(i).getProductId().equals(CartList.get(j).getProductId())){
                    CartList.get(i).setAmount(CartList.get(i).getAmount()+ CartList.get(j).getAmount());
                    CartList.remove(j);
                }

            }
        }

        Total=0;
        for(Cart c:CartList){
            Total+=c.getPrice()*c.getAmount();
        }
        if(Total!=0){
        CartTotalPrice.setText(String.valueOf(Total) +" + "+ serviceProvider.setTotalServiceCharge(Total));}
        else{
            CartTotalPrice.setText(String.valueOf( "0.00"));
        }


    }

    public  void callabelrefrasher(){
        cartRefrasher();
    }

    public void initializeCart(){
      cartRefrasher();
       CartName.setCellValueFactory(new PropertyValueFactory<>("CUsername"));
       CartPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
       CartAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
       CartProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
       CartTable.setItems(CartList);



    }



    @FXML
    void CartPayBtn(MouseEvent event) {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        String time=format.format(date);
        if(sample.fulldoortodoordeliveryapp.SourceCode.Customer.getCustomerfromDB(StoreData.Info.getUserName()).getAvailableBallace()>=(Total+serviceProvider.setTotalServiceCharge(Total))){
        if(CartList.size()==0 || CartPickUpLocation.getText().isEmpty()){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You should complete the whole info required to buy a product");
            alert.showAndWait();
        }
        else{

            for(Cart c:CartList){
                Orders ord=new Orders(c.getCUsername(),c.getProductId(),CartPickUpLocation.getText(),time,"OnProcess",c.getAmount());
                ord.addOrdertoDB();
                Cart.DeleteCartDb(c.getProductId(),c.getCUsername());

            }

            for(Cart c:CartList){
                    serviceProvider.paymentForSuperMarket(c.getProductId(),c.getAmount()*c.getPrice());
                    serviceProvider.DeductProductAmount(c.getAmount(),c.getProductId());
                }



        }






        serviceProvider.DeductCustomerBallance(StoreData.Info.getUserName(),Total+serviceProvider.setTotalServiceCharge(Total));
        serviceProvider.setServiceProvidersBallance(serviceProvider.setTotalServiceCharge(Total));
        cartRefrasher();}
       else {
           alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Information");
           alert.setHeaderText(null);
           alert.setContentText("Your balance is insufficient refund your account ");
           alert.showAndWait();
       }
        CartPickUpLocation.clear();

    }


    /**
     *
     *
     * orderStatusPage
     */


    @FXML
    private TableColumn<Orders, Byte> OrderStatusTableImage;
    @FXML
    private TableColumn<Orders,Integer> OrderStatusAmount;


    @FXML
    private ImageView OrderStatusInvoiceImage;

    @FXML
    private TableColumn<Orders,String> OrderStatusOS;

    @FXML
    private TableColumn<Orders, String> OrderStatusPickUpLocatio;

    @FXML
    private TableColumn<Orders,String> OrderStatusProductId;

    @FXML
    private TableView<Orders> OrderStatusTable;

    @FXML
    private TableColumn<Orders,String> OrderStatusTimeOfPurchasse;
    @FXML
    private TextField OrderStatustTextFildTP;

   ObservableList<Orders> OrderStatusList=FXCollections.observableArrayList();
    void refrasheOrderStatusTable(){
        OrderStatusList.clear();
        OrderStatusList.addAll(Orders.getOrderDB(StoreData.Info.getUserName()));

    }

    void initializeOrderStatusTable(){
        refrasheOrderStatusTable();

        OrderStatusAmount.setCellValueFactory(new PropertyValueFactory<>("AmountofProduct"));;
        OrderStatusOS.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
        OrderStatusPickUpLocatio.setCellValueFactory(new PropertyValueFactory<>("pickUpLocation"));
        OrderStatusProductId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        OrderStatusTimeOfPurchasse.setCellValueFactory(new PropertyValueFactory<>("TimeOfPurchase"));
        OrderStatusTableImage.setCellValueFactory(new PropertyValueFactory<>("invoiceImage"));
        OrderStatusTable.setItems(OrderStatusList);


    }


    @FXML
    void OrderStatusTableClicked(MouseEvent event) {
      Orders ordersStatus=OrderStatusTable.getSelectionModel().getSelectedItem();
      try{
        ByteArrayInputStream byt = new ByteArrayInputStream(ordersStatus.getInvoiceImage());
        Image fx = new Image(byt);
        OrderStatusInvoiceImage.setImage(fx);
        OrderStatustTextFildTP.setText(ordersStatus.getTimeOfPurchase());}
      catch (NullPointerException e){
          OrderStatusInvoiceImage.setImage(null);
          alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setTitle("information");
          alert.setContentText("Invoice not Uploaded Yet");
          alert.showAndWait();
      }

    }

    @FXML
    void OrderStatusProductAcceptedBTN(MouseEvent event) {
        Orders.SetOrderStatus("Accepted",StoreData.Info.getUserName(),OrderStatustTextFildTP.getText() );
        serviceProvider.deliveryManBallance();
        refrasheOrderStatusTable();

    }

    @FXML
    private Label AccountBallance;
    @FXML
    private  TextField FuncTextfild;

    void CustomerBallance(){
        Float ballance= sample.fulldoortodoordeliveryapp.SourceCode.Customer.getCustomerfromDB(StoreData.Info.getUserName()).getAvailableBallace();
        AccountBallance.setText("$"+ballance);
    }

    @FXML
    void FuncAcountbtn(MouseEvent event) {
        try{
        sample.fulldoortodoordeliveryapp.SourceCode.Customer.updateCustomerBallance(StoreData.Info.getUserName(),Float.parseFloat(FuncTextfild.getText()));
        CustomerBallance();
        FuncTextfild.clear();
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Inf");
        alert.setContentText("Account Funded");
        alert.showAndWait();}
        catch (NumberFormatException e){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("you should Enter a numeric values");
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        composeImage();
        initializeName();
        SetItemstoGrid();
        initializeCart();
        initializeOrderStatusTable();
        CustomerBallance();

    }


}
