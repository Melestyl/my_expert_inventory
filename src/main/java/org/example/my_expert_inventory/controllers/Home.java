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
			loadScene("create-inventory-select-bien.fxml", createInventory);
		} catch (IOException e) {
			e.printStackTrace();
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
	void setCreateInventory() {
		System.out.println("Create Inventory");
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("Home");
	}
}
