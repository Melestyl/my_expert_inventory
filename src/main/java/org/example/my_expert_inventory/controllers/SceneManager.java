package org.example.my_expert_inventory.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.my_expert_inventory.HelloApplication;
import org.example.my_expert_inventory.model.Bien;

import java.io.IOException;

public class SceneManager {

    @FXML
    public static Initializable loadScene(String fxmlFile, Node node) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }
}
