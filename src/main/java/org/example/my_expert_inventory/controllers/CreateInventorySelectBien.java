package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.my_expert_inventory.HelloApplication;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.service.BienService;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateInventorySelectBien implements Initializable{

	List<Bien> biens;

    @FXML private VBox eltVbox;
    @FXML private Button backToHome;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String buttonStyle = "-fx-padding: 15 15 15; -fx-min-width: 650;";

        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        biens = bienService.findAllBiens();
        for (Bien bien : biens) {
            System.out.println(bien);
            Button button = new Button(bien.toString());
            button.setStyle(buttonStyle);
            button.setOnAction(e -> {
                System.out.println(bien);
            });
            eltVbox.getChildren().add(button);
        }
    }

    @FXML
    private Initializable loadScene(String fxmlFile, Node node) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }

    @FXML
    public void setBackToHome() {
        System.out.println("Back to Home");
        try {
            loadScene("home.fxml", backToHome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
