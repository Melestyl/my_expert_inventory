package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.example.my_expert_inventory.dao.ElementDAO;
import org.example.my_expert_inventory.dao.EtatDesLieuxDAO;
import org.example.my_expert_inventory.dao.MinuteDAO;
import org.example.my_expert_inventory.dao.TypeDElementDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.EtatDesLieux;
import org.example.my_expert_inventory.model.Minute;
import org.example.my_expert_inventory.service.ElementService;
import org.example.my_expert_inventory.service.EtatDesLieuxService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewInventory implements Initializable {
	static public EtatDesLieux etatDesLieux;

	private EtatDesLieuxService etatDesLieuxService;

	private List<Minute> minutes;

	@FXML private Button backToHome;
	@FXML private GridPane gridPane;
	private int row = 0;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("View Inventory");
		System.out.println(etatDesLieux);

		etatDesLieuxService = new EtatDesLieuxService(new EtatDesLieuxDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new MinuteDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));

		minutes = etatDesLieuxService.findMinutesByEtatDesLieux(etatDesLieux);
		for (Minute minute : minutes) {
			// Add a row to the gridPane
			gridPane.addRow(
					row++,
					new Label(minute.getIdElement().getIdPiece().getTypeDePiece()),
					new Label(minute.getIdElement().getIdTypeElement().getType()),
					new Label(minute.getEtatElement()),
					new Label(minute.getCommentaire())
			);
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
