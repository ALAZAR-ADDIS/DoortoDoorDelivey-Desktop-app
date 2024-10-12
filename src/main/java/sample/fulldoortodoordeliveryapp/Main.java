package sample.fulldoortodoordeliveryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.fulldoortodoordeliveryapp.SourceCode.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

import static sample.fulldoortodoordeliveryapp.SourceCode.DatabaseConnection.con;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new DatabaseConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        try{
        con.close();
            }
        catch (SQLException e){
            System.out.println(e);
        }
    }


}