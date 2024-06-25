package org.example.my_expert_inventory.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.example.my_expert_inventory.service.BienService;

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

    void setAddRow() {
        // Add a new row to the table
    }

    void setSaveElements() {
        // Save the elements in the table
    }

    void setRemoveLastRow() {
        // Remove the last row from the table
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
