module sample.fulldoortodoordeliveryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires  java.xml;
    requires java.desktop;
    requires  javafx.base;


    opens  sample.fulldoortodoordeliveryapp.SourceCode to javafx.base;
    opens sample.fulldoortodoordeliveryapp to javafx.fxml;
    exports sample.fulldoortodoordeliveryapp;
}