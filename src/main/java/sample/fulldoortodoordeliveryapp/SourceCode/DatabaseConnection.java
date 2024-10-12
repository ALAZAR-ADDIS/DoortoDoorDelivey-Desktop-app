package sample.fulldoortodoordeliveryapp.SourceCode;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection{
    public static Connection con=null;
    String dConnection="jdbc:sqlserver://localhost;databasename=DoortoDoorDelivery;trustServerCertificate=true";
    String dUsername="DoortoDoorDelivery";
    String dPassword="DoortoDoorDelivery";
    public DatabaseConnection(){
        try{
            con= DriverManager.getConnection(dConnection,dUsername,dPassword);
            System.out.println("connected successfully");
        }catch (SQLException e){
            System.out.println(e);
        }
    }



}