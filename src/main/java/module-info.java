module carmengitit.ws03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.carmengitit.ws03 to javafx.fxml;
    opens com.carmengitit.ws03.model to javafx.base;
    exports com.carmengitit.ws03;
    exports com.carmengitit.ws03.controller;
    opens com.carmengitit.ws03.controller to javafx.fxml;
}