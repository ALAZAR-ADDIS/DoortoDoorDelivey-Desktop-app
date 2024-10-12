package sample.fulldoortodoordeliveryapp;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.fulldoortodoordeliveryapp.SourceCode.Cart;
import sample.fulldoortodoordeliveryapp.SourceCode.product;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class Item implements Initializable {
    private Customer customerController;

    public void setCustomerController(Customer customerController) {
        this.customerController = customerController;
    }

    @FXML
    private Spinner<Integer> ItemAmout;

    @FXML
    private Label ItemDescription;

    @FXML
    private ImageView ItemImage;

    @FXML
    private Label ItemName;

    @FXML
    private Label ItemPrice;

    @FXML
    private Label PoductId;

    private product prod;

    SpinnerValueFactory<Integer> spn=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000,1);



    void InitializeProduct(product p){
        this.prod=p;
        setItemInfo();

    }

    void setItemInfo(){
        ItemDescription.setText(prod.getProductDescription());
        ItemName.setText(prod.getProductName());
        PoductId.setText(prod.getProductId());
        ItemPrice.setText("$" +String.valueOf(prod.getPrice()));
        ByteArrayInputStream byt = new ByteArrayInputStream(prod.getProductImage());
        Image fx = new Image(byt);
        ItemImage.setImage(fx);
    }
    @FXML
    void ItemAddToCart(MouseEvent event) {
        Cart cart=new Cart(StoreData.Info.getUserName(),ItemName.getText(),ItemAmout.getValue(),Float.parseFloat(ItemPrice.getText().substring(1)), PoductId.getText());
        cart.addProductToCartDb();
        customerController.callabelrefrasher();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemAmout.setValueFactory(spn);
    }
}
