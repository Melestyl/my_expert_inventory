package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.service.BienService;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateInventorySelectBien implements Initializable{

    List<Bien> biens;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        biens = bienService.findAllBiens();
        for (Bien bien : biens) {
            System.out.println(bien);
        }

    }
}
