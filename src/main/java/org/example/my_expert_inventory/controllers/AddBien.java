package org.example.my_expert_inventory.controllers;

import com.sun.jdi.StringReference;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.service.BienService;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class AddBien implements Initializable {
    private BienService.TypeDeBien typeDeBien;
    private String proprietaire;
    private String rue;
    private String ville;
    private int numeroRue;
    private int codePostal;
    private String complementAdresse;

    public static Bien monBien;

    @FXML
    private Button saveBien;
    @FXML
    private Button backToHome;
    @FXML
    private ChoiceBox<BienService.TypeDeBien> choiceTypeBien;
    @FXML
    private TextField getProprietaire;
    @FXML
    private TextField getRue;
    @FXML
    private TextField getVille;
    @FXML
    private TextField getNumeroRue;
    @FXML
    private TextField getCodePostal;
    @FXML
    private TextField getComplementAdresse;
    @FXML
    private Label textError;

    @FXML
    void setBackToHome() {
        System.out.println("Back to Home");
        try {
            SceneManager.loadScene("home.fxml", backToHome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setSaveBien(){
        textError.setText("");
        System.out.println("Save Bien");
        proprietaire = getProprietaire.getText();
        if (proprietaire.isEmpty()) {
            textError.setText("Formulaire incomplet");
            return;
        }
        typeDeBien = choiceTypeBien.getValue();
        if (typeDeBien == null) {
            textError.setText("Formulaire incomplet");
            return;
        }
        try {
            numeroRue = Integer.parseInt(getNumeroRue.getText());
        } catch (NumberFormatException e) {
            textError.setText("Formulaire incomplet");
            return;
        }
        rue = getRue.getText();
        if (rue.isEmpty()) {
            textError.setText("Formulaire incomplet");
            return;
        }
        try {
            codePostal = Integer.parseInt(getCodePostal.getText());
        } catch (NumberFormatException e) {
            textError.setText("Formulaire incomplet");
            return;
        }
        ville = getVille.getText();
        if (ville.isEmpty()) {
            textError.setText("Formulaire incomplet");
            return;
        }
        complementAdresse = getComplementAdresse.getText();

        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        Bien bien = bienService.creatBienWithAdresse(typeDeBien, proprietaire, numeroRue, rue, codePostal, ville, complementAdresse);
        if (bien == null) {
            textError.setText("Erreur lors de la création du bien, adresse déjà utilisée");
            return;
        }
        bien.toString();

        monBien = bien;
        System.out.println("Bien créé");
        try {
            SceneManager.loadScene("add-piece.fxml", saveBien);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Créez une liste standard Java à partir des valeurs de l'énumération
        List<BienService.TypeDeBien> typesDeBiens = Arrays.asList(BienService.TypeDeBien.values());
        // Convertissez la liste standard Java en ObservableList
        ObservableList<BienService.TypeDeBien> observableTypesDeBiens = FXCollections.observableArrayList(typesDeBiens);
        // Utilisez l'ObservableList pour remplir le ChoiceBox
        choiceTypeBien.setItems(observableTypesDeBiens);
    }
}
