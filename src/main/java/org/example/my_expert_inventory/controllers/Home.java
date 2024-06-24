package org.example.my_expert_inventory.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
	void setAddBien() throws IOException {
		System.out.println("Add Bien");
		try {
			SceneManager.loadScene("add-bien.fxml", addBien);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void setViewInventory() throws IOException {
		System.out.println("View Inventory");
		try {
			SceneManager.loadScene("create-inventory-select-bien.fxml", viewInventory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void setCreateInventory() {
		System.out.println("Create Inventory");
		{
			System.out.println("View Inventory");
			try {
				SceneManager.loadScene("create-inventory-select-bien.fxml", createInventory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
}
