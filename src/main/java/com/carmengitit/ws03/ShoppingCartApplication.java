package com.carmengitit.ws03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShoppingCartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCartApplication.class.getResource("shopping-cart-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}