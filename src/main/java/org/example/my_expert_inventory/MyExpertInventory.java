package org.example.my_expert_inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyExpertInventory extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyExpertInventory.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 512);
        stage.setTitle("My Expert Inventory (French v1.0)");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}