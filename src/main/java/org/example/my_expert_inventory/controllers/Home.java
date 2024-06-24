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

	// Sout something when viewInventory button is clicked
	@FXML
	protected void onViewInventoryButtonClick() {
		System.out.println("View Inventory button clicked!");
	}
}
