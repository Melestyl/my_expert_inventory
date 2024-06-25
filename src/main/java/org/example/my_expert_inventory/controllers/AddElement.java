package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.dao.ElementDAO;
import org.example.my_expert_inventory.dao.PieceDAO;
import org.example.my_expert_inventory.dao.TypeDElementDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.Element;
import org.example.my_expert_inventory.model.Piece;
import org.example.my_expert_inventory.model.TypeDElement;
import org.example.my_expert_inventory.service.BienService;
import org.example.my_expert_inventory.service.ElementService;
import org.example.my_expert_inventory.service.PieceService;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddElement implements Initializable {

    @FXML
    private GridPane table;

    @FXML
    private Button addRow;

    @FXML
    private Button saveElements;

    @FXML
    private Button removeLastRow;

    public static Bien bien;

    public static ObservableList<Piece> pieces;

    private int indexCount = 0;

    private List<TypeDElement> typesDElements;

    @FXML
    void setAddRow() {
        // Add a new row to the table
        if (indexCount > 0) {
            if( ((ChoiceBox) table.getChildren().get(indexCount * table.getColumnCount()+1)).getValue() == null || ((ChoiceBox) table.getChildren().get(indexCount * table.getColumnCount()+2)).getValue() == null){
                return;
            }
        }
        System.out.println("Add Row");

        //création d'une nouvelle ligne

        //préparation d'un choicebox pour afficher les types d'éléments contenu dans la liste typesDElements.
        // On garde en mémoire pour chaque ligne l'objet TypeDElement sélectionné, mais on affiche seulement son attribut type
        ChoiceBox<TypeDElement> choiceTypeDElement = new ChoiceBox<>();
        choiceTypeDElement.setItems(FXCollections.observableArrayList(typesDElements));
        choiceTypeDElement.setConverter(new javafx.util.StringConverter<TypeDElement>() {
            @Override
            public String toString(TypeDElement typeDElement) {
                if (typeDElement!= null) {
                    return typeDElement.getType();
                } else {
                    return "";
                }
            }

            @Override
            public TypeDElement fromString(String s) {
                return null;
            }
        });

        //préparation d'un choicebox pour afficher pieces contenu dans la liste pieces.
        // On garde en mémoire pour chaque ligne l'objet Piece sélectionné, mais on affiche seulement son attribut typeDePiece + " : " + ordreVisite
        ChoiceBox<Piece> choicePiece = new ChoiceBox<>();
        choicePiece.setItems(pieces);
        choicePiece.setConverter(new javafx.util.StringConverter<Piece>() {
            @Override
            public String toString(Piece piece) {
                if (piece!= null) {
                    return piece.getTypeDePiece() + " : " + piece.getOrdreVisite();
                } else {
                    return "";
                }
            }

            @Override
            public Piece fromString(String s) {
                return null;
            }
        });

        //ajout des éléments à la ligne
        table.addRow(++indexCount, choicePiece, choiceTypeDElement);
    }

    @FXML
    void setSaveElements() {
        //vérification si les champs de la dernière ligne sont remplis, sinon on annule l'enregistrement
        if(indexCount < 1 || ((ChoiceBox) table.getChildren().get(indexCount * table.getColumnCount() + 1)).getValue() == null || ((ChoiceBox) table.getChildren().get(indexCount * table.getColumnCount() + 2)).getValue() == null){
            return;
        }
        System.out.println("Save Elements");
        ElementService elementService = new ElementService(new ElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()),new TypeDElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        for(int i = 0; i < indexCount; i++){


            Piece piece = ((ChoiceBox<Piece>) table.getChildren().get((i+1) * table.getColumnCount() + 1)).getValue();
            TypeDElement typeDElement = ((ChoiceBox<TypeDElement>) table.getChildren().get((i+1) * table.getColumnCount() + 2)).getValue();

            elementService.createElement(typeDElement, piece);

            try {
                SceneManager.loadScene("home.fxml", saveElements);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }

    @FXML
    void setRemoveLastRow() {
        System.out.println("Remove Last Row");

        //vérification si on peut supprimer la dernière ligne
        if(indexCount > 0){
            //suppression de la dernière ligne
            table.getChildren().remove(indexCount * table.getColumnCount() + 2);
            table.getChildren().remove(indexCount * table.getColumnCount() + 1);
            //décrémentation du compteur de ligne
            indexCount--;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Retrieve the list of pieces from the Bien object
        bien = AddPiece.bien;
        pieces = AddPiece.pieces;

        //Get the list of type elements
        ElementService elementService = new ElementService(new ElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()),new TypeDElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        typesDElements = elementService.findAllTypeDElement();

        // Add the rows to the table
        table.addRow(indexCount, new Label("Piece"), new Label("Type d'Element"));

    }
}
