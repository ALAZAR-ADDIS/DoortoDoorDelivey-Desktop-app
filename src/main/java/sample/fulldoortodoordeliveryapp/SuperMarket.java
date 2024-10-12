package sample.fulldoortodoordeliveryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.fulldoortodoordeliveryapp.SourceCode.Orders;
import sample.fulldoortodoordeliveryapp.SourceCode.product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SuperMarket implements Initializable {

    byte[] decomposedProductImage;
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










    @FXML
    private ImageView SuperMarketProfilePic;

    @FXML
    private Label SuperMarkerFullName;

    @FXML
    private AnchorPane SuperMarkerManageProduct;

    @FXML
    private AnchorPane SuperMarketBallance;

    @FXML
    private AnchorPane SuperMarketIncomingOrders;

    @FXML
    private Label SuperMarketUserName;

    @FXML
    void BackToLogin(MouseEvent event) throws IOException {
        Stage CurrentStage=(Stage)SuperMarkerFullName.getScene().getWindow();
        CurrentStage.close();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene=new Scene(loader.load());
        Stage stage=new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void SuperMarketBallance(MouseEvent event) {
        SuperMarketBallance.setVisible(true);
        SuperMarketIncomingOrders.setVisible(false);
        SuperMarkerManageProduct.setVisible(false);

    }

    @FXML
    void SuperMarketIncomingOrders(MouseEvent event) {
        SuperMarketBallance.setVisible(false);
        SuperMarketIncomingOrders.setVisible(true);
        SuperMarkerManageProduct.setVisible(false);

    }

    @FXML
    void SuperMarketManagePage(MouseEvent event) {
        SuperMarketBallance.setVisible(false);
        SuperMarketIncomingOrders.setVisible(false);
        SuperMarkerManageProduct.setVisible(true);
    }


    void composeImage(){

        ByteArrayInputStream byt = new ByteArrayInputStream(StoreData.Info.getimage());
        Image fx = new Image(byt);
        SuperMarketProfilePic.setImage(fx);
    }

    void initializeName(){
        SuperMarketUserName.setText(StoreData.Info.getUserName());
        String FirstName=StoreData.Info.getFirstName();
        FirstName=StoreData.Info.getFirstName().substring(0,1).toUpperCase()+StoreData.Info.getFirstName().substring(1).toLowerCase();
        SuperMarkerFullName.setText(FirstName);
    }


    @FXML
    private ImageView ProductImage;
    @FXML
    private TableColumn<product, Integer> ManageProductAmountAvailable;

    @FXML
    private TableColumn<product, String> ManageProductId;

    @FXML
    private TableColumn<product,String> ManageProductName;

    @FXML
    private TableColumn<product, Float> ManageProductPrice;

    @FXML
    private TableColumn<product, Integer> ManageProductSoldOutProducts;

    @FXML
    private TableView<product> ManageProductTable;

    @FXML
    private TableColumn<product, String> ManageproductDescription;
    @FXML
    private TableColumn<product, byte[]> ManageTableImage;


    /**
     * Text fild
     */
    @FXML
    private TextField TextProductAmountAvailable;

    @FXML
    private TextField TextProductDescription;

    @FXML
    private TextField TextProductId;

    @FXML
    private TextField TextProductName;

    @FXML
    private TextField TextProductNumberOfSoldOut;

    @FXML
    private TextField TextProductPrice;

    ObservableList<product> ProductLise= FXCollections.observableArrayList();

    void refreshTable(){
        ProductLise.clear();
        NoticeBordRefrasher();
        ArrayList<product> SuperMarkerProducts=((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket)(StoreData.Info)).getProductFromDb();
        System.out.println("Hello");
        for(product p:SuperMarkerProducts){
            ProductLise.add(p);
        }
    }

    void Tableinitealizer(){
        refreshTable();
        ManageProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        ManageproductDescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        ManageProductId.setCellValueFactory((new PropertyValueFactory<>("productId")));
        ManageProductAmountAvailable.setCellValueFactory(new PropertyValueFactory<>("numberAvailable"));
        ManageProductSoldOutProducts.setCellValueFactory(new PropertyValueFactory<>("productSoldOut"));
        ManageProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ManageTableImage.setCellValueFactory(new PropertyValueFactory<>("productImage"));
        ManageProductTable.setItems(ProductLise);

    }

    @FXML
    void AddProduct(MouseEvent event) {
        if( TextProductName.getText().isEmpty() ||TextProductDescription.getText().isEmpty() || TextProductId.getText().isEmpty() || TextProductAmountAvailable.getText().isEmpty() ||TextProductNumberOfSoldOut.getText().isEmpty() ||TextProductPrice.getText().isEmpty()|| ProductImage.getImage()==null){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Fill  out all the details of the product");
            alert.showAndWait();
        }
        else {
            try{
            product p = new product(StoreData.Info.getUserName(), TextProductName.getText(), TextProductDescription.getText(), TextProductId.getText(), Integer.parseInt(TextProductAmountAvailable.getText()), Integer.parseInt(TextProductNumberOfSoldOut.getText()), Float.parseFloat(TextProductPrice.getText()), decomposedProductImage);
            ((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket) (StoreData.Info)).setProducts(p);
            ((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket) (StoreData.Info)).addProductstoDb();

            refreshTable();
            clear();}
            catch (NumberFormatException e){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Please  use  a proper numeric values for the AmountAvailable,NumberOfSoldout,Price text filed ");
                alert.showAndWait();
            }
        }
    }
    @FXML
    void DeleteProduct(MouseEvent event) {
        product p=new product(StoreData.Info.getUserName(), TextProductName.getText(),TextProductDescription.getText(),TextProductId.getText(),Integer.parseInt(TextProductAmountAvailable.getText()),Integer.parseInt(TextProductNumberOfSoldOut.getText()),Float.parseFloat(TextProductPrice.getText()),decomposedProductImage);
        ((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket)(StoreData.Info)).setProducts(p);
        ((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket)(StoreData.Info)).deleteProductfromDb();
        refreshTable();
        clear();
    }

    @FXML
    void ManageProductTableClicked(MouseEvent event) {
        product p=ManageProductTable.getSelectionModel().getSelectedItem();
        TextProductName.setText(p.getProductName());
        TextProductDescription.setText(p.getProductDescription());
        TextProductId.setText(p.getProductId());
        TextProductAmountAvailable.setText(String.valueOf(p.getNumberAvailable()));
        TextProductNumberOfSoldOut.setText(String.valueOf(p.getProductSoldOut()));
        TextProductPrice.setText(String.valueOf(p.getPrice()));
        System.out.println(p.getProductImage());
        decomposedProductImage=p.getProductImage();
        ByteArrayInputStream byt = new ByteArrayInputStream(decomposedProductImage);
        Image fx = new Image(byt);
        ProductImage.setImage(fx);


    }

    @FXML
    void ProductImageClicked(MouseEvent event) {
        decomposedProductImage=imageTobyte();
        ByteArrayInputStream byt = new ByteArrayInputStream(decomposedProductImage);
        Image fx = new Image(byt);
        ProductImage.setImage(fx);
    }


    @FXML
    void UpdateProduct(MouseEvent event) {

        if( TextProductName.getText().isEmpty() ||TextProductDescription.getText().isEmpty() || TextProductId.getText().isEmpty() || TextProductAmountAvailable.getText().isEmpty() ||TextProductNumberOfSoldOut.getText().isEmpty() ||TextProductPrice.getText().isEmpty()||  ProductImage.getImage()==null){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Fill  out all the details of the product");
            alert.showAndWait();
        }
        else {
            try{
                product p=new product(StoreData.Info.getUserName(), TextProductName.getText(),TextProductDescription.getText(),TextProductId.getText(),Integer.parseInt(TextProductAmountAvailable.getText()),Integer.parseInt(TextProductNumberOfSoldOut.getText()),Float.parseFloat(TextProductPrice.getText()),decomposedProductImage);
                ((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket)(StoreData.Info)).setProducts(p);
                ((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket)(StoreData.Info)).updateProducts();//db
                refreshTable();
                clear();}
            catch (NumberFormatException e){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Please  use  a proper numeric values for the AmountAvailable,NumberOfSoldout,Price text filed ");
                alert.showAndWait();
            }
        }


    }


    void clear(){
        TextProductName.clear();
        TextProductDescription.clear();
        TextProductId.clear();
        TextProductAmountAvailable.clear();
        TextProductNumberOfSoldOut.clear();
        TextProductPrice.clear();
        ProductImage.setImage(null);
    }
    @FXML
    void Clear(MouseEvent event) {
          clear();
    }


    /**
     *
     *notic bord code
     */

    @FXML
    private TableColumn<product,Integer> NoticeBordAmountAvailable;

    @FXML
    private TableColumn<product,Integer> NoticeBordNumberSoldout;

    @FXML
    private TableColumn<product,String> NoticeBordProductId;

    @FXML
    private TableColumn<product,String> NoticeBordProductName;

    @FXML
    private TableView<product> NoticeBordTable;

    @FXML
    private TableColumn<product, Float> NoticebordPrice;

    @FXML
    private TableColumn<product, String> NoticebordProductDexcription;


    ObservableList NoticeProductList=FXCollections.observableArrayList();
    void NoticeBordRefrasher(){
        NoticeProductList.clear();
        ArrayList<product> products=((sample.fulldoortodoordeliveryapp.SourceCode.SuperMarket)(StoreData.Info)).getNoticeProductFromDb();
        for(product p:products){
            NoticeProductList.add(p);
        }

    }

    void NOticeeBrordInitializer(){
        NoticeBordRefrasher();
        NoticeBordProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        NoticebordProductDexcription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        NoticeBordProductId.setCellValueFactory((new PropertyValueFactory<>("productId")));
        NoticeBordAmountAvailable.setCellValueFactory(new PropertyValueFactory<>("numberAvailable"));
        NoticeBordNumberSoldout.setCellValueFactory(new PropertyValueFactory<>("productSoldOut"));
        NoticebordPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        NoticeBordTable.setItems(NoticeProductList);
    }




    /*
    incoming orders Page
     */
    @FXML
    private  Label SuperMarketBallanceLabel;
    @FXML
    private TableColumn<Orders,Integer> IOAmount;

    @FXML
    private TableColumn<Orders, String> IOCusername;

    @FXML
    private TableColumn<Orders,String> IOOrderStatus;

    @FXML
    private TableColumn<Orders, String> IOProuctId;

    @FXML
    private TableView<Orders> IOTable;

    @FXML
    private TableColumn<Orders, String> IOTimePurchase;

    ObservableList<Orders> IncomingOrdesList=FXCollections.observableArrayList();
    void incomingOrderRefrashor(){
        IncomingOrdesList.addAll(Orders.getincomingorders(StoreData.Info.getUserName()));

    }
    void initializerForIncominord(){
        incomingOrderRefrashor();
        IOAmount.setCellValueFactory(new PropertyValueFactory<>("AmountofProduct"));
        IOCusername.setCellValueFactory(new PropertyValueFactory<>("CUserName"));
       IOOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
         IOProuctId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        IOTimePurchase.setCellValueFactory(new PropertyValueFactory<>("TimeOfPurchase"));
        IOTable.setItems(IncomingOrdesList);



    }


    /*
    ballance page
     */

    void showSuperMarketBallance(){
        SuperMarketBallanceLabel.setText(String.valueOf(StoreData.Info.getAvailableBallace()));
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
       initializeName();
       composeImage();
       Tableinitealizer();
       NOticeeBrordInitializer();
       initializerForIncominord();
       showSuperMarketBallance();
    }




}
