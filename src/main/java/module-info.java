module ngnvzn.com.demologin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ngnvzn.com.demologin to javafx.fxml;
    exports ngnvzn.com.demologin;
}