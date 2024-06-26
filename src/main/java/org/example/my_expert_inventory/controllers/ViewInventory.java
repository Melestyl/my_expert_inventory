package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
	private String piece;

	@FXML private Button backToHome;
	@FXML private GridPane gridPane;
	private int row = 0;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("View Inventory");
		System.out.println(etatDesLieux);

		etatDesLieuxService = new EtatDesLieuxService(new EtatDesLieuxDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new MinuteDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
		piece = "";

		minutes = etatDesLieuxService.findMinutesByEtatDesLieux(etatDesLieux);
		for (Minute minute : minutes) {
			if (!minute.getIdElement().getIdPiece().getTypeDePiece().equals(piece)) {
				piece = minute.getIdElement().getIdPiece().getTypeDePiece();

				// Add empty row
				gridPane.addRow(row++, new Label(""), new Label(""), new Label(""));

				// Add row with headers
				Label pieceLabel = new Label(piece);
				pieceLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
				pieceLabel.setStyle("-fx-font-weight: bold;");
				Label etatLabel = new Label("Etat");
				etatLabel.setAlignment(javafx.geometry.Pos.CENTER);
				etatLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
				etatLabel.setStyle("-fx-font-weight: bold;");
				Label commentaireLabel = new Label("Commentaire");
				commentaireLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
				commentaireLabel.setStyle("-fx-font-weight: bold;");

				gridPane.addRow(
						row++,
						pieceLabel,
						etatLabel,
						commentaireLabel
				);
			}
			// Add a row to the gridPane with the element
			gridPane.addRow(
					row++,
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
