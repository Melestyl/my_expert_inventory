package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.dao.PieceDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.Piece;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.example.my_expert_inventory.service.BienService;
import org.example.my_expert_inventory.service.PieceService;


public class AddPiece implements Initializable{

    @FXML
    private TableView<Piece> tablePiece;

    @FXML
    private Button addPiece;

    @FXML
    private GridPane test;

    @FXML
    private Button savePieces;

    @FXML
    private Button removeLastRow;

    private int indexCount = 0;

    List<Piece> listPiece;

    public static Bien bien;

    public static ObservableList<Piece> pieces;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        test.addRow(indexCount, new Label("Type de pièce"), new Label("Surface"));
        listPiece = FXCollections.observableArrayList();
        bien=AddBien.monBien;
    }

    @FXML
    void setAddPiece() {
        if (indexCount > 0) {
            if(((TextField) test.getChildren().get(indexCount * test.getColumnCount()+2)).getText().isEmpty() || ((ChoiceBox) test.getChildren().get(indexCount * test.getColumnCount()+1)).getValue() == null){
                return;
            }
        }

        //création d'une nouvelle ligne

        //préparation d'un choicebox pour le type de pièce
        ChoiceBox<PieceService.TypeDePiece> choiceTypePiece = new ChoiceBox<>();
        List<PieceService.TypeDePiece> typesDeBiens = Arrays.asList(PieceService.TypeDePiece.values()); // Créez une liste standard Java à partir des valeurs de l'énumération
        ObservableList<PieceService.TypeDePiece> observableTypesDePiece = FXCollections.observableArrayList(typesDeBiens); // Convertissez la liste standard Java en ObservableList
        choiceTypePiece.setItems(observableTypesDePiece); // Utilisez l'ObservableList pour remplir le ChoiceBox

        //préparation d'un textfield pour la surface
        TextField surface = new TextField();
        //permet de n'accepter que des chiffres
        surface.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                surface.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        //ajout des éléments à la ligne
        test.addRow(++indexCount, choiceTypePiece, surface);

        //ajout d'une nouvelle pièce à la liste
        listPiece.add(new Piece());
    }

    @FXML
    void setSavePieces(){
        //vérification si les champs de la dernière ligne sont remplis, sinon on annule l'enregistrement
        if(indexCount < 1 || ((TextField) test.getChildren().get(indexCount * test.getColumnCount() + 2)).getText().isEmpty() || ((ChoiceBox) test.getChildren().get(indexCount * test.getColumnCount() + 1)).getValue() == null){
            return;
        }
        //récupération des données des pièces et enrégistrement dans la base de données
        PieceService pieceService = new PieceService(new PieceDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        for(int i = 0; i < indexCount; i++){
            //vérification si les champs de la dernière ligne sont remplis, sinon on ne peut pas ajouter une nouvelle ligne
            System.out.println(test.getChildren().get((i+1) * test.getColumnCount()+1).toString());
            System.out.println(test.getChildren().get((i+1) * test.getColumnCount()+2).toString());
            listPiece.get(i);
            PieceService.TypeDePiece typeDePiece = PieceService.TypeDePiece.valueOf((((ChoiceBox<PieceService.TypeDePiece>) test.getChildren().get((i+1) * test.getColumnCount() + 1)).getValue().toString()));
            Integer surface = Integer.parseInt(((TextField) test.getChildren().get((i+1) * test.getColumnCount() + 2)).getText());

            pieceService.createPiece(typeDePiece, surface,i+1, bien);

            try {
                SceneManager.loadScene("add-element.fxml", savePieces);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void setRemoveLastRow(){
        //vérification si on peut supprimer la dernière ligne
        if(indexCount > 0){
            //suppression de la dernière ligne
            test.getChildren().remove(indexCount * test.getColumnCount() + 2);
            test.getChildren().remove(indexCount * test.getColumnCount() + 1);
            //décrémentation du compteur de ligne
            indexCount--;
        }
    }

}
