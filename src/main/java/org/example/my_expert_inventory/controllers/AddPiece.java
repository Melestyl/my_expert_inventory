package org.example.my_expert_inventory.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.my_expert_inventory.service.BienService;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class AddPiece implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(AddBien.monBien.toString());
    }
}
