package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.service.BienService;
import org.example.my_expert_inventory.controllers.SceneManager;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewInventorySelectBien implements Initializable {
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
                try {
                    ViewInventorySelectInventory.bien = bien;
                    SceneManager.loadScene("view-inventory-select-inventory.fxml", eltVbox);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            eltVbox.getChildren().add(button);
        }
    }

    @FXML
    public void setBackToHome() {
        System.out.println("Back to Home");
        try {
            SceneManager.loadScene("home.fxml", backToHome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
