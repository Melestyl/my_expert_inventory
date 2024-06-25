package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.example.my_expert_inventory.dao.*;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.Element;
import org.example.my_expert_inventory.model.EtatDesLieux;
import org.example.my_expert_inventory.model.Piece;
import org.example.my_expert_inventory.service.ElementService;
import org.example.my_expert_inventory.service.EtatDesLieuxService;
import org.example.my_expert_inventory.service.PieceService;

import java.net.URL;
import java.util.*;

public class CreateInventory implements Initializable {
	public static Bien bien;

	private EtatDesLieuxService etatDesLieuxService;
	private PieceService pieceService;

	private List<Element> elements;
	private EtatDesLieux etatDesLieux;

	List<EtatDesLieuxService.EtatElement> etatElements;
	ObservableList<EtatDesLieuxService.EtatElement> observableEtat;

	// Create an observable list of enum
	EtatDesLieuxService.TypeEtatDesLieux[] typeEtatDesLieux = EtatDesLieuxService.TypeEtatDesLieux.values();

	@FXML public Button backToHome;
	@FXML public RadioButton entreeBtn;
	@FXML public GridPane gridPane;
	private int row = 0;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("Create Inventory");
		System.out.println(bien);

		etatElements = Arrays.asList(EtatDesLieuxService.EtatElement.values());
		observableEtat = FXCollections.observableArrayList(etatElements);

		etatDesLieuxService = new EtatDesLieuxService(new EtatDesLieuxDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new MinuteDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
		pieceService = new PieceService(new PieceDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));

		etatDesLieux = new EtatDesLieux();
		elements = new ArrayList<>();

		etatDesLieux = etatDesLieuxService.createEtatDesLieux(EtatDesLieuxService.TypeEtatDesLieux.ENTREE);

		// When entreeBtn is changed, update the type of etatDesLieux
		entreeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				etatDesLieux = etatDesLieuxService.updateTypeEtatDesLieux(etatDesLieux, EtatDesLieuxService.TypeEtatDesLieux.ENTREE);
				System.out.println("Etat des lieux passé en entrée");
			} else {
				etatDesLieux = etatDesLieuxService.updateTypeEtatDesLieux(etatDesLieux, EtatDesLieuxService.TypeEtatDesLieux.SORTIE);
				System.out.println("Etat des lieux passé en sortie");
			}
		});

		List<Piece> pieces = pieceService.findPiecesByBien(bien);
		ElementService elementService = new ElementService(new ElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new TypeDElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
		for (Piece piece : pieces) {
			// Get elements of the piece
			elements = elementService.findByPiece(piece);

			// Add a row to the gridPane
			gridPane.addRow(row++, new Label(piece.getTypeDePiece() + " (" + piece.getSurface() + "m²)"), new Label("Etat"), new Label("Commentaire"));
			System.out.println(piece.getTypeDePiece());

			for (Element element : elements) {
				ComboBox<EtatDesLieuxService.EtatElement> comboBox = new ComboBox<>(observableEtat);
				TextField textField = new TextField();

				// When textField loses focus, sout the new value
				textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue) {
						if (comboBox.getValue() != null) {
							try {
								etatDesLieuxService.createMinute(textField.getText(), comboBox.getValue(), element, etatDesLieux);
								System.out.println("Envoyé à la BDD");
							} catch (InterruptedException e) {
								throw new RuntimeException(e);
							}
						}
					}
				});
				// When comboBox is modified, sout the new value
				comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
					try {
						etatDesLieuxService.createMinute(textField.getText(), comboBox.getValue(), element, etatDesLieux);
						System.out.println("Envoyé à la BDD");
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				});

				// Add a row to the gridPane
				gridPane.addRow(row++, new Label(element.getIdTypeElement().getType()), comboBox, textField);
				System.out.println(element.getIdTypeElement().getType());
			}
		}
	}

	@FXML
	public void setBackToHome() {
		try {
			SceneManager.loadScene("home.fxml", backToHome);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML void switchType() {
		etatDesLieux = etatDesLieuxService.updateTypeEtatDesLieux(etatDesLieux, EtatDesLieuxService.TypeEtatDesLieux.ENTREE);
		System.out.println("Etat des lieux passé en entrée");
	}
}
