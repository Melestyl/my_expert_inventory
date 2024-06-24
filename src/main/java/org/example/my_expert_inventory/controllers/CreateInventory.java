package org.example.my_expert_inventory.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.my_expert_inventory.model.Bien;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateInventory implements Initializable {
	public static Bien bien;

	@FXML
	public Button backToHome;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("Create Inventory");
		System.out.println(bien);
	}

	@FXML
	public void setBackToHome() {
		try {
			SceneManager.loadScene("home.fxml", backToHome);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
