package org.example.my_expert_inventory.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.my_expert_inventory.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
	@FXML
	private Button addBien;

	@FXML
	private Button createInventory;

	@FXML
	private Button viewInventory;

	@FXML
	void setAddBien() {
		System.out.println("Add Bien");
	}

	@FXML
	void setViewInventory() throws IOException {
		System.out.println("View Inventory");
		try {
			SceneManager.loadScene("create-inventory-select-bien.fxml", createInventory);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void setCreateInventory() {
		System.out.println("Create Inventory");
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("Home");
	}
}
