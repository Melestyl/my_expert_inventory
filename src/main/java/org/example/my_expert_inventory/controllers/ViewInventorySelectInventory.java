package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.my_expert_inventory.dao.EtatDesLieuxDAO;
import org.example.my_expert_inventory.dao.MinuteDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.EtatDesLieux;
import org.example.my_expert_inventory.service.EtatDesLieuxService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewInventorySelectInventory implements Initializable {
	static public Bien bien;

	private List<EtatDesLieux> etatsDesLieux;

	@FXML private VBox eltVbox;
	@FXML private Button backToHome;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		String buttonStyle = "-fx-padding: 15 15 15; -fx-min-width: 650;";

		EtatDesLieuxService etatDesLieuxService = new EtatDesLieuxService(new EtatDesLieuxDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new MinuteDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
		etatsDesLieux = etatDesLieuxService.findEtatDesLieuxByBien(bien);
		for (EtatDesLieux etat : etatsDesLieux) {
			System.out.println(etat);
			Button button = new Button(etat.toString());
			button.setStyle(buttonStyle);
			button.setOnAction(e -> {
				try {
					ViewInventory.etatDesLieux = etat;
					SceneManager.loadScene("view-inventory.fxml", eltVbox);
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
