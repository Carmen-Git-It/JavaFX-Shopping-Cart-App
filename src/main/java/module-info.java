module carmengitit.ws03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens carmengitit.ws03 to javafx.fxml;
    exports carmengitit.ws03;
}