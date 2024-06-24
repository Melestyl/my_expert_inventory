package org.example.my_expert_inventory.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Home {
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
	void setViewInventory() {
		System.out.println("View Inventory");
	}

	@FXML
	void setCreateInventory() {
		System.out.println("Create Inventory");
	}
}
